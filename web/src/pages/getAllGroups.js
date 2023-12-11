import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewGroups extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGroupsToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGroupsToPage);
        this.header = new Header(this.dataStore);
        console.log("viewgroups constructor");
    }

    /**
     * Once the client is loaded, get the groups list.
     */
    async clientLoaded() {
        console.log("clientLoaded running")
        const groups = await this.client.getAllGroups();
        console.log("API Response: ", groups);
        this.dataStore.set('groups', groups);
        this.addGroupsToPage();
    }

    /**
     * Add the header to the page and load the TrueAchievementsGroupClient.
     */
    mount() {
        this.header.addHeaderToPage();
        this.client = new TrueAchievementsGroupClient();
        this.clientLoaded();
    }

    /**
     * When the group is updated in the datastore, update the group metadata on the page.
     */
    addGroupsToPage() {
        console.log("addgroupsstopage");
        const groups = this.dataStore.get('groups');
        console.log("Retrieved groups:", groups);

        if (!groups) {
            console.log("Groups is null");
            return;
        }

        const sortedGroups = groups.map(group => {
            const sortedGamesList = group.gamesList ? [...group.gamesList].sort((a, b) => a.gameName.localeCompare(b.gameName)) : [];
            return { ...group, gamesList: sortedGamesList };
        });

        const groupList = document.getElementById("group-list");
        console.log("groupList:", groupList);
        groupList.innerHTML = ''; // Clear the previous content

        sortedGroups.forEach((group) => {
            console.log("Rendering group:", group);
            const encodedGroupName = encodeURIComponent(group.groupName);
            const groupListItem = document.createElement('div');
            groupListItem.classList.add('group-item');

            let gamesListHtml = '';
            if (group.gamesList && Array.isArray(group.gamesList)) {
                const displayedGames = group.gamesList.slice(0, 5); // Display only the first 5 games
                gamesListHtml = `
                <div class="games-list">Games List:</div>
                <ul class="game-list-items">
                    ${displayedGames.map(game => `<li>${game.gameName}</li>`).join('')}
                    ${group.gamesList.length > 5 ? '<li>...</li>' : ''}
                </ul>
            `;
            } else {
                gamesListHtml = '<div class="no-games">No games available.</div>';
            }

            groupListItem.innerHTML = `
        <h2><a href="/group.html?groupName=${encodedGroupName}">${group.groupName}</a></h2>
        <dl>
            ${gamesListHtml}
        </dl>
    `;
            groupList.appendChild(groupListItem);
        });
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewGroups = new ViewGroups();
    viewGroups.mount();
};

window.addEventListener('DOMContentLoaded', main);