import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view group page of the website.
 */
class ViewGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGroupToPage', 'addGamesToPage', 'addGame', 'deleteGame', 'populateGameDropdown'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGroupToPage);
        this.dataStore.addChangeListener(this.addGamesToPage);
        this.header = new Header(this.dataStore);
        console.log("viewgroup constructor");

        this.addGame = this.addGame.bind(this);
    }

    /**
     * Once the client is loaded, get the group metadata and game list.
     */
    async clientLoaded() {
        try {
          const urlParams = new URLSearchParams(window.location.search);
          const encodedName = urlParams.get('groupName');
          const groupName = decodeURIComponent(encodedName);
          document.getElementById('group-name').innerText = 'Loading group ...';
          const group = await this.client.getGroup(groupName);
          this.dataStore.set('group', group);
          const gamesResponse = await this.client.getAllGames();
          const games = gamesResponse.game || [];
          if (!Array.isArray(games)) {
              throw new Error('Invalid game data received');
          }
          this.dataStore.set('games', games);
          await this.populateGameDropdown();
        } catch (error) {
          console.error('Error loading group:', error);
          throw error;
        }
      }


    /**
     * Add the header to the page and load the TrueAchievementsGroupClient.
     */
    mount() {
        try {
            document.getElementById('delete-game').addEventListener('click', this.deleteGame);
            document.getElementById('add-game').addEventListener('click', this.addGame);
            this.header.addHeaderToPage();
            this.client = new TrueAchievementsGroupClient();
            this.clientLoaded();
            this.populateGameDropdown();

        } catch (error) {
            console.error('Error loading group:', error);
        }
    }


    /**
     * When the group is updated in the datastore, update the group metadata on the page.
     */
    addGroupToPage() {
        try {
            const group = this.dataStore.get('group');

            if (!group || !group.groupName) {
                console.error('Group is undefined.');
                document.getElementById('group-name').innerText = 'N/A';
                return;
            }

            document.getElementById('group-name').innerText = group.groupName || 'N/A';

        } catch (error) {
            console.error('Error fetching group:', error);
            document.getElementById('group-name').innerText = "Error fetching group";
        }
    }

    /**
     * When the games are updated in the datastore, update the list of games on the page.
     */
     addGamesToPage() {
      try {
        console.log('addgamestopage');
        const group = this.dataStore.get('group');
        const gamesList = group.gamesList || [];

        if (gamesList.length === 0) {
          console.log('null games');
          document.getElementById('games-list').innerHTML = 'No games available.';
          return;
        }

        const sortedGamesList = gamesList.sort((a, b) => a.gameName.localeCompare(b.gameName));

          const gamesListHtml = sortedGamesList.map(game => `
            <li>
                <h2><a href="/game.html?uniqueId=${game.uniqueId}">
                    ${game.gameName || ''} 
                </a></h2>
            </li>
        `).join('');

          document.getElementById('games-list').innerHTML = `<ul>${gamesListHtml}</ul>`;
      } catch (error) {
        console.error('Error adding games to page:', error);
      }
    }

    /**
     * Method to run when the add game to group submit button is pressed. Call the TrueAchievementsGroupService to add a game to the group.
     */

    async addGame() {
        try {
            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            const group = this.dataStore.get('group');

            if (!group || !group.groupName) {
                console.error('Group is undefined or does not have a name.');
                return;
            }

            const uniqueId = document.getElementById('add-game-dropdown').value;

            if (!uniqueId) {
                errorMessageDisplay.innerText = 'Please select a game.';
                errorMessageDisplay.classList.remove('hidden');
                return;
            }

            document.getElementById('add-game').innerText = 'Adding...';

            // Call the client to add the game to the group
            const updatedGroup = await this.client.addGameToGroup(group.groupName, uniqueId, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            // Update the group data in the DataStore
            this.dataStore.set('group', updatedGroup);

            // Reset the form and button text
            document.getElementById('add-game').innerText = 'Add Game';
            document.getElementById('add-game-form').reset();

            console.log('After updating group and games on the page');
            window.location.reload();
        } catch (error) {
            console.error('Error adding contact:', error);
        }
    }

    /**
     * Method to run when the delete game from group button is pressed.
     * Call the TrueAchievementsGroupService to delete a game from the group.
     */
    async deleteGame() {
        try {
            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            const group = this.dataStore.get('group');
            if (!group || !group.groupName) {
                console.error('Group is undefined or does not have a name.');
                return;
            }

            const uniqueId = document.getElementById('delete-game-dropdown').value;
            if (!uniqueId) {
                errorMessageDisplay.innerText = 'Please select a game.';
                errorMessageDisplay.classList.remove('hidden');
                return;
            }

            document.getElementById('delete-game').innerText = 'Deleting...';

            // Call the client to delete the game from the group
            const updatedGroup = await this.client.deleteGameFromGroup(group.groupName, uniqueId, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            // Update the group data in the DataStore
            this.dataStore.set('group', updatedGroup);

            // Reset the button text
            document.getElementById('delete-game').innerText = 'Delete Game';
            document.getElementById('delete-game-form').reset();

            window.location.reload();
        } catch (error) {
            console.error('Error deleting game:', error);
        }
    }

    async populateGameDropdown() {
        try {
            const games = this.dataStore.get('games');
            const group = this.dataStore.get('group');

            if (!games || !group) {
                console.log("No games or group found in DataStore");
                return;
            }

            // Clear both dropdowns
            document.getElementById('add-game-dropdown').innerHTML = '';
            document.getElementById('delete-game-dropdown').innerHTML = '';

            games.forEach((game) => {
                const option = document.createElement('option');
                option.value = game.uniqueId;
                option.textContent = `${game.gameName}`.trim();

                // Add the option to the "Add Game" dropdown
                document.getElementById('add-game-dropdown').appendChild(option);

                // Only add the option to the "Delete Game" dropdown if the game is assigned to the group
                if (group.gamesList && group.gamesList.some((assignedGame) => assignedGame.uniqueId === game.uniqueId)) {
                    document.getElementById('delete-game-dropdown').appendChild(option.cloneNode(true));
                }
            });
        } catch (error) {
            console.error('Error fetching games:', error);
        }
    }

    }


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewGroup = new ViewGroup();
    await viewGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);