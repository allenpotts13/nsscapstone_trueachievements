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
        this.bindClassMethods(['clientLoaded', 'mount', 'addGroupToPage', 'addGamesToPage', 'addGame', 'populateGameDropdown'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGroupToPage);
        this.dataStore.addChangeListener(this.addGamesToPage);
        this.header = new Header(this.dataStore);
        console.log("viewgroup constructor");

        this.addContact = this.addContact.bind(this);
    }

    /**
     * Once the client is loaded, get the group metadata and game list.
     */
    async clientLoaded() {
        try {
          const urlParams = new URLSearchParams(window.location.search);
          const encodedName = urlParams.get('name');
          const groupName = decodeURIComponent(encodedName);
          document.getElementById('group-name').innerText = 'Loading group ...';
          const group = await this.client.getGroup(groupName);
          this.dataStore.set('group', group);
          const games = await this.client.getAllGames();
          this.dataStore.set('games', games);
          this.populateContactDropdown();
        } catch (error) {
          console.error('Error loading group:', error);
        }
      }


    /**
     * Add the header to the page and load the TrueAchievementsGroupClient.
     */
    mount() {
        document.getElementById('add-contact').addEventListener('click', this.addContact);

        this.header.addHeaderToPage();
        this.client = new TrueAchievementsGroupClient();
        this.clientLoaded();
        this.populateContactDropdown();
        }

    /**
     * When the group is updated in the datastore, update the group metadata on the page.
     */
    addGroupToPage() {
        try {
            const group = this.dataStore.get('group');

            document.getElementById('group-name').innerText = group.name || 'N/A';

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

        const gamesListHtml = gamesList.map((game) => `
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
             if (!group) {
                 return;
             }

         const uniqueId = document.getElementById('game-dropdown').value;

             if (!uniqueId) {
                 errorMessageDisplay.innerText = 'Please select a game.';
                 errorMessageDisplay.classList.remove('hidden');
                 return;
             }
             console.log('Data being sent in Axios request:', {
                 group: group.name,
                 uniqueId: uniqueId,
             });

         document.getElementById('add-game').innerText = 'Adding...';

           // Call the client to add the game to the group
         const updatedGroup = await this.client.addGameToGroup(group.name, uniqueId, (error) => {
         errorMessageDisplay.innerText = `Error: ${error.message}`;
         errorMessageDisplay.classList.remove('hidden');
         });
           // Update the group data in the DataStore
         this.dataStore.set('group', updatedGroup);

           // Reset the form and button text
          document.getElementById('add-game').innerText = 'Add Game';
          document.getElementById('add-game-form').reset();

          window.location.reload();
         } catch (error) {
             console.error('Error adding contact:', error);
         }
         }

    async populateGameDropdown() {
        try {
             const games = this.dataStore.get('games');

             if (!games) {
                  console.log("No games found in DataStore");
                  return;
             }
            const dropdown = document.getElementById('games-dropdown');

            games.forEach((games) => {
                const option = document.createElement('option');
                option.value = games.uniqueId;

                // Display the games name (gameName)
                option.textContent = `${games.gameName}`.trim();
                dropdown.appendChild(option);
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
    viewGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);