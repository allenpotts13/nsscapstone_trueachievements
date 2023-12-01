import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view all games page of the website.
 */
class ViewGames extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGamesToPage','updatePagination', 'updateGamesPerPage', 'handleSearch'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGamesToPage);
        this.header = new Header(this.dataStore);
        this.currentPage = 1;
        this.gamesPerPage = 10;
        this.filteredGames = null;
        console.log("viewgames constructor");
    }

    /**
     * Once the client is loaded, get the game list.
     */
    async clientLoaded() {
        const games = await this.client.getAllGames();
        this.dataStore.set('games', games);
        this.filteredGames = games;
    }

    /**
     * Add the header to the page and load the TrueAchievementGroupClient.
     */
    async mount() {
        await this.header.addHeaderToPage();
        this.client = new TrueAchievementsGroupClient();
        await this.clientLoaded();
        this.setupControls();
    }

    setupControls() {
        // Add controls such as dropdown for games per page and search bar
        const controlsContainer = document.getElementById('controls');
        controlsContainer.innerHTML = '';

        // Dropdown for games per page
        const gamesPerPageDropdown = document.createElement('select');
        gamesPerPageDropdown.addEventListener('change', () => {
            this.gamesPerPage = parseInt(gamesPerPageDropdown.value);
            this.currentPage = 1; // Reset to the first page when changing games per page
            this.addGamesToPage();
        });

        // Populate games per page dropdown with options
        [5, 10, 20, 50].forEach((option) => {
            const optionElement = document.createElement('option');
            optionElement.value = option.toString();
            optionElement.textContent = option.toString();
            gamesPerPageDropdown.appendChild(optionElement);
        });

        controlsContainer.appendChild(gamesPerPageDropdown);

        // Search bar
        const searchInput = document.createElement('input');
        searchInput.type = 'text';
        searchInput.placeholder = 'Search...';
        searchInput.addEventListener('input', this.handleSearch.bind(this));

        controlsContainer.appendChild(searchInput);

        // Initial setup of pagination and games
        this.addGamesToPage();
    }


    /**
     * When the games list is updated in the datastore, update the games metadata on the page.
     */
    addGamesToPage() {
        console.log("addgamestopage")
        const startIndex = (this.currentPage - 1) * this.gamesPerPage;
        const endIndex = startIndex + this.gamesPerPage;
        const paginatedGames = this.filteredGames.slice(startIndex, endIndex);

        const gamesList = document.getElementById('games-list');
        gamesList.innerHTML = ''; // Clear previous games

        for (const game of paginatedGames) {
            // ... (Same game element creation logic as before)
        }

        this.updatePagination();
    }

    updatePagination() {
        const totalPages = Math.ceil(this.filteredGames.length / this.gamesPerPage);

        const paginationContainer = document.getElementById('pagination');
        paginationContainer.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            const pageButton = document.createElement('button');
            pageButton.textContent = i.toString();
            pageButton.addEventListener('click', () => {
                this.currentPage = i;
                this.addGamesToPage();
            });
            paginationContainer.appendChild(pageButton);
        }
    }

    updateGamesPerPage() {
        this.gamesPerPage = parseInt(document.getElementById('gamesPerPageDropdown').value);
        this.currentPage = 1; // Reset to the first page when changing games per page
        this.addGamesToPage();
    }

    handleSearch() {
        const searchInput = document.getElementById('searchInput');
        const searchTerm = searchInput.value.toLowerCase();

        // Filter games based on the search term
        this.filteredGames = this.dataStore.get('games').filter((game) => {
            const gameName = game.gameName.toLowerCase();
            return gameName.includes(searchTerm);
        });

        this.currentPage = 1; // Reset to the first page when performing a search
        this.addGamesToPage();
    }
}

const main = async () => {
    const viewGames = new ViewGames();
    await viewGames.mount();
};

window.addEventListener('DOMContentLoaded', main);