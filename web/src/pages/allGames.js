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
        this.bindClassMethods(['clientLoaded', 'mount', 'setupControls', 'addGamesToPage', 'handlePageButtonClick', 'createGameElement', 'updatePagination', 'updateGamesPerPage', 'handleSearch'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGamesToPage);
        this.header = new Header(this.dataStore);
        this.currentPage = 1;
        this.gamesPerPage = 5;
        this.filteredGames = [];
        console.log("viewgames constructor");
    }

    /**
     * Once the client is loaded, get the game list.
     */
    async clientLoaded() {
        try {
            console.log('this.client', this.client);
            const response = await this.client.getAllGames();
            const games = (response && response.game)  || [];

            if (games.length === 0) {
                console.error("No games retrieved from the server");
                return;
            }
            this.dataStore.set('games', games);
            this.filteredGames = games;
        } catch (error) {
            console.error("Error loading games:", error);
        }
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
        const controlsContainer = document.getElementById('search-container');
        controlsContainer.innerHTML = '';

        // Dropdown for games per page
        this.setupGamesPerPageDropdown()

        // Search bar
        const searchInput = document.createElement('input');
        searchInput.id = 'search-field'; // Set a unique ID
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
        console.log("addgamestopage");

        if (this.filteredGames == null || this.filteredGames.length === 0) {
            console.log("No games to display");
            return;
        }

        const startIndex = (this.currentPage - 1) * this.gamesPerPage;
        const endIndex = startIndex + this.gamesPerPage;
        const paginatedGames = this.filteredGames.slice(startIndex, endIndex);

        const gamesList = document.getElementById('games-list');
        gamesList.innerHTML = ''; // Clear previous games

        if (paginatedGames.length === 0) {
            console.log("No games in the current page");
            return;
        }

        for (const game of paginatedGames) {
            const gameElement = this.createGameElement(game);
            gamesList.appendChild(gameElement);
        }

        this.updatePagination();
    }

    handlePageButtonClick(pageNumber) {
        this.currentPage = pageNumber;
        this.addGamesToPage();
    }

    createGameElement(game) {
        const gameElement = document.createElement('div');
        gameElement.classList.add('game');

        const titleElement = document.createElement('h2');
        titleElement.textContent = game.gameName;
        titleElement.style.color = '#ffcc00';
        gameElement.appendChild(titleElement);

        titleElement.addEventListener('click', () => {
            window.location.href = `game.html?uniqueId=${encodeURIComponent(game.uniqueId)}`;
        });

        gameElement.appendChild(titleElement);

        const platformElement = document.createElement('p');
        platformElement.textContent = `Platform: ${game.platform}`;
        gameElement.appendChild(platformElement);

        const gameURLElement = document.createElement('p');
        gameURLElement.textContent = `Game URL: ${game.gameURL} / ${game.gameURL}`;
        gameElement.appendChild(gameURLElement);

        const linkElement = document.createElement('a');
        linkElement.href = game.gameURL;
        linkElement.textContent = 'View Details';
        linkElement.style.color = '#3498db';
        linkElement.target = '_blank';
        gameElement.appendChild(linkElement);

        return gameElement;
    }

    updatePagination() {
        const totalPages = Math.ceil(this.filteredGames.length / this.gamesPerPage);

        const paginationContainer = document.getElementById('pagination');
        if (!paginationContainer) {
            console.log('No pagination container found');
            return;
        }
        paginationContainer.innerHTML = '';

        const maxVisibleButtons = 5; // Adjust this value as needed

        const startPage = Math.max(1, this.currentPage - Math.floor(maxVisibleButtons / 2));
        const endPage = Math.min(totalPages, startPage + maxVisibleButtons - 1);

        if (startPage > 1) {
            this.addPaginationButton('<<', Math.max(1, this.currentPage - maxVisibleButtons));
        }

        for (let i = startPage; i <= endPage; i++) {
            this.addPaginationButton(i.toString(), i);
        }

        if (endPage < totalPages) {
            this.addPaginationButton('>>', Math.min(totalPages, this.currentPage + maxVisibleButtons));
        }
    }

    addPaginationButton(label, pageNumber) {
        const paginationContainer = document.getElementById('pagination');
        const pageButton = document.createElement('button');
        pageButton.textContent = label;
        pageButton.addEventListener('click', () => {
            this.handlePageButtonClick(pageNumber);
        });
        paginationContainer.appendChild(pageButton);
    }

    updateGamesPerPage() {
        const gamesPerPageDropdown = document.getElementById('gamesPerPageDropdown');

        if (!gamesPerPageDropdown) {
            console.error('Games per page dropdown not found');
            return;
        }

        this.gamesPerPage = parseInt(gamesPerPageDropdown.value);
        this.currentPage = 1; // Reset to the first page when changing games per page
        this.addGamesToPage();
    }

    setupGamesPerPageDropdown() {
        // Dropdown for games per page
        const gamesPerPageDropdownContainer = document.getElementById('gamesPerPageDropdown-container');
        gamesPerPageDropdownContainer.innerHTML = ''; // Clear previous dropdown

        const label = document.createElement('label');
        label.htmlFor = 'gamesPerPageDropdown';
        label.textContent = 'Games per Page:';

        const gamesPerPageDropdown = document.createElement('select');
        gamesPerPageDropdown.id = 'gamesPerPageDropdown';
        gamesPerPageDropdown.addEventListener('change', () => {
            this.updateGamesPerPage();
        });

        // Populate games per page dropdown with options
        [5, 10, 20, 50].forEach((option) => {
            const optionElement = document.createElement('option');
            optionElement.value = option.toString();
            optionElement.textContent = option.toString();
            gamesPerPageDropdown.appendChild(optionElement);
        });

        gamesPerPageDropdownContainer.appendChild(label);
        gamesPerPageDropdownContainer.appendChild(gamesPerPageDropdown);
    }

    handleSearch() {
        const searchInput = document.getElementById('search-field');
        const gamesPerPageDropdown = document.getElementById('gamesPerPageDropdown');

        if (!searchInput || !gamesPerPageDropdown) {
            console.log('No search input or games per page dropdown found');
            return;
        }

        const searchTerm = searchInput.value.toLowerCase();

        // Filter games based on the search term
        this.filteredGames = this.dataStore.get('games').filter((game) => {
            const gameName = game.gameName ? game.gameName.toLowerCase() : '';
            return gameName.includes(searchTerm);
        });

        // Reset to the first page when performing a search
        this.currentPage = 1;

        // Update games per page if needed
        const selectedGamesPerPage = parseInt(gamesPerPageDropdown.value);
        if (selectedGamesPerPage !== this.gamesPerPage) {
            this.gamesPerPage = selectedGamesPerPage;
        }

        // Update the UI
        this.addGamesToPage();
    }
}

const main = async () => {
    const viewGames = new ViewGames();
    await viewGames.mount();
};

window.addEventListener('DOMContentLoaded', main);