import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class TrueAchievementsGroupClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createGroup', 'getAllGames',
            'getAllGroups', 'getGame', 'getGamesInGroup', 'getGroup', 'addGameToGroup', 'deleteGameFromGroup', 'getUserStats'];

        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        await this.authenticator.login();
    }

    async logout() {
        await this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }
    /**
     * Create a new group owned by the current user.
     * @param groupName The name of the group to create.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The group that has been created.
     */

    async createGroup(groupName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create groups.");
            const response = await this.axiosClient.post(`groups`, {
                groupName: groupName
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.group;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get all the games for the current user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of games for the current user.
     */

    async getAllGames(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see all contacts.");
            const response = await this.axiosClient.get(`games`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log('API Response in getAllGames:', response.data)
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get all the groups for the current user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of groups for the current user.
     */

    async getAllGroups(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see all groups.");
            const response = await this.axiosClient.get(`groups`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.groupList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the game for the given userId.
     * @param uniqueId Unique identifier for a game.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The game's metadata.
     */
    async getGame(uniqueId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view contacts.");
            const url = `games/${uniqueId}`;

            console.log('API Request in getGame:', {
                method: 'GET',
                url: this.axiosClient.defaults.baseURL + url,
                headers: {
                    Authorization: `Bearer ${token}`,
                    // Add any other headers as needed
                },
            });

            const response = await this.axiosClient.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            console.log('API Response in getGame:', response.data);
            return response.data.game;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    /**
     * Get the games in a given group by the group's name.
     * @param groupName Unique identifier for a group
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of games in a group.
     */
    async getGamesInGroup(groupName, errorCallback) {
        try {
            const response = await this.axiosClient.get(`groups/${groupName}/games`);
            return response.data.gamesList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get the group by the group's name.
     * @param groupName Unique identifier for a group
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The group's metadata.
     */
    async getGroup(groupName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view contacts.");
            const response = await this.axiosClient.get(`groups/${groupName}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.group;
        } catch (error) {
            console.error('Error fetching group:', error);
            this.handleError(error, errorCallback)
            throw error;
        }
    }
    /**
     * Method to add a contact to an existing group.
     * @param uniqueId - The unique ID to be added.
     * @param groupName - The name of the group.
     * @param errorCallback - Callback function to handle errors.
     * @returns The game being added to the group.
     */
    async addGameToGroup(groupName, uniqueId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow(
                "Only authenticated users can add a contact to a group."
            );

            const response = await this.axiosClient.post(
                `groups/${groupName}/games`,
                {
                    groupName: groupName,
                    uniqueId: uniqueId
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            console.log('addGameToGroup response:', response);
            return response;
        } catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    /**
     * Get the user stats for the current user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user stats for the current user.
     */
    async getUserStats(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see their stats.");
            const response = await this.axiosClient.get(`statistics`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.stats;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Pulls in a group and deletes games from the games list
     * @param groupName - The name of the group.
     * @param uniqueId - The unique ID to be deleted.
     * @param errorCallback - Callback function to handle errors.
     * @returns The game being deleted from the group.
     */
    async deleteGameFromGroup(groupName, uniqueId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a game from a group.");
            const response = await this.axiosClient.delete(
                `groups/${groupName}/games/${uniqueId}`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            return response;
        } catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}