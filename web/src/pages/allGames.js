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
            console.log('API Response in clientLoaded:', response);

            const games = (response && response.game)  || [];

            if (games.length === 0) {
                console.error("No games retrieved from the server");
                return;
            }

            console.log("clientloaded", games);

            this.dataStore.set('games', games);
            const storedGames = this.dataStore.get('games');
            console.log("DataStore after setting games", storedGames);
            this.filteredGames = games;
            console.log("DataStore after setting games", this.dataStore.getState());
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
        console.log('Client after initialization: ', this.client);
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

        const achievementsWonNoDlcElement = document.createElement('p');
        achievementsWonNoDlcElement.textContent = `Achievements Won (No DLC): ${game.achievementsWonNoDlc}`;
        gameElement.appendChild(achievementsWonNoDlcElement);

        const maxAchievementsNoDlcElement = document.createElement('p');
        maxAchievementsNoDlcElement.textContent = `Max Achievements (No DLC): ${game.maxAchievementsNoDlc}`;
        gameElement.appendChild(maxAchievementsNoDlcElement);

        const achievementsWonIncludeDlcElement = document.createElement('p');
        achievementsWonIncludeDlcElement.textContent = `Achievements Won (Include DLC): ${game.achievementsWonIncludeDlc}`;
        gameElement.appendChild(achievementsWonIncludeDlcElement);

        const maxAchievementsIncludeDlcElement = document.createElement('p');
        maxAchievementsIncludeDlcElement.textContent = `Max Achievements (Include DLC): ${game.maxAchievementsIncludeDlc}`;
        gameElement.appendChild(maxAchievementsIncludeDlcElement);

        const gamerScoreWonNoDlcDlcElement = document.createElement('p');
        gamerScoreWonNoDlcDlcElement.textContent = `Gamer Score Won (No DLC): ${game.gamerScoreWonNoDlc}`;
        gameElement.appendChild(gamerScoreWonNoDlcDlcElement);

        const maxGamerScoreNoDlcElement = document.createElement('p');
        maxGamerScoreNoDlcElement.textContent = `Max Gamer Score (No DLC): ${game.maxGamerScoreNoDlc}`;
        gameElement.appendChild(maxGamerScoreNoDlcElement);

        const gamerScoreWonIncludeDlcElement = document.createElement('p');
        gamerScoreWonIncludeDlcElement.textContent = `Gamer Score Won (Include DLC): ${game.gamerScoreWonIncludeDlc}`;
        gameElement.appendChild(gamerScoreWonIncludeDlcElement);

        const maxGamerScoreIncludeDlcElement = document.createElement('p');
        maxGamerScoreIncludeDlcElement.textContent = `Max Gamer Score (Include DLC): ${game.maxGamerScoreIncludeDlc}`;
        gameElement.appendChild(maxGamerScoreIncludeDlcElement);

        const trueAchievementWonNoDlcElement = document.createElement('p');
        trueAchievementWonNoDlcElement.textContent = `True Achievement Won (No DLC): ${game.trueAchievementWonNoDlc}`;
        gameElement.appendChild(trueAchievementWonNoDlcElement);

        const maxTrueAchievementNoDlcElement = document.createElement('p');
        maxTrueAchievementNoDlcElement.textContent = `Max True Achievement (No DLC): ${game.maxTrueAchievementNoDlc}`;
        gameElement.appendChild(maxTrueAchievementNoDlcElement);

        const trueAchievementWonIncludeDlcElement = document.createElement('p');
        trueAchievementWonIncludeDlcElement.textContent = `True Achievement Won (Include DLC): ${game.trueAchievementWonIncludeDlc}`;
        gameElement.appendChild(trueAchievementWonIncludeDlcElement);

        const maxTrueAchievementIncludeDlcElement = document.createElement('p');
        maxTrueAchievementIncludeDlcElement.textContent = `Max True Achievement (Include DLC): ${game.maxTrueAchievementIncludeDlc}`;
        gameElement.appendChild(maxTrueAchievementIncludeDlcElement);

        const myCompletionPercentageElement = document.createElement('p');
        myCompletionPercentageElement.textContent = `My Completion Percentage: ${game.myCompletionPercentage}`;
        gameElement.appendChild(myCompletionPercentageElement);

        const completionDateElement = document.createElement('p');
        completionDateElement.textContent = `Completion Date: ${game.completionDate}`;
        gameElement.appendChild(completionDateElement);

        const challengesWonElement = document.createElement('p');
        challengesWonElement.textContent = `Challenges Won: ${game.challengesWon}`;
        gameElement.appendChild(challengesWonElement);

        const maxChallengesElement = document.createElement('p');
        maxChallengesElement.textContent = `Max Challenges: ${game.maxChallenges}`;
        gameElement.appendChild(maxChallengesElement);

        const hoursPlayedElement = document.createElement('p');
        hoursPlayedElement.textContent = `Hours Played: ${game.hoursPlayed}`;
        gameElement.appendChild(hoursPlayedElement);

        const myRatingElement = document.createElement('p');
        myRatingElement.textContent = `My Rating: ${game.myRating}`;
        gameElement.appendChild(myRatingElement);

        const siteRatingElement = document.createElement('p');
        siteRatingElement.textContent = `Site Rating: ${game.siteRating}`;
        gameElement.appendChild(siteRatingElement);

        const myRatioElement = document.createElement('p');
        myRatioElement.textContent = `My Ratio: ${game.myRatio}`;
        gameElement.appendChild(myRatioElement);

        const siteRatioElement = document.createElement('p');
        siteRatioElement.textContent = `Site Ratio: ${game.siteRatio}`;
        gameElement.appendChild(siteRatioElement);

        const ownershipStatusElement = document.createElement('p');
        ownershipStatusElement.textContent = `Ownership Status: ${game.ownershipStatus}`;
        gameElement.appendChild(ownershipStatusElement);

        const playStatusElement = document.createElement('p');
        playStatusElement.textContent = `Play Status: ${game.playStatus}`;
        gameElement.appendChild(playStatusElement);

        const formatElement = document.createElement('p');
        formatElement.textContent = `Format: ${game.format}`;
        gameElement.appendChild(formatElement);

        const completionEstimateElement = document.createElement('p');
        completionEstimateElement.textContent = `Completion Estimate: ${game.completionEstimate}`;
        gameElement.appendChild(completionEstimateElement);

        const walkthroughElement = document.createElement('p');
        walkthroughElement.textContent = `Walkthrough: ${game.walkthrough}`;
        gameElement.appendChild(walkthroughElement);

        const gameNotesElement = document.createElement('p');
        gameNotesElement.textContent = `Game Notes: ${game.gameNotes}`;
        gameElement.appendChild(gameNotesElement);

        const contestStatusElement = document.createElement('p');
        contestStatusElement.textContent = `Contest Status: ${game.contestStatus}`;
        gameElement.appendChild(contestStatusElement);


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
            this.addPaginationButton('<<', Math.max(1, this.currentPage - maxVisibleButtons)); // Add first page button or ellipsis
        }

        for (let i = startPage; i <= endPage; i++) {
            this.addPaginationButton(i.toString(), i);
        }

        if (endPage < totalPages) {
            this.addPaginationButton('>>', Math.min(totalPages, this.currentPage + maxVisibleButtons)); // Add last page button or ellipsis
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