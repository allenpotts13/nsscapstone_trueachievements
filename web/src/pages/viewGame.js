import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view game page of the website.
 */
class ViewGame extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGameToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGameToPage);
        this.header = new Header(this.dataStore);
        console.log("viewgame constructor");
    }

    /**
     * Once the client is loaded, get the game metadata.
     */
    async clientLoaded() {
        try {
            const urlParams = new URLSearchParams(window.location.search);
            const encodedUniqueId = urlParams.get('uniqueId');
            const uniqueId = decodeURIComponent(encodedUniqueId);
            document.getElementById('game-gameName').innerText = 'Loading game ...';
            const game = await this.client.getGame(uniqueId);
            console.log("game: ", game)
            this.dataStore.set('game', game);
        } catch (error) {
            console.error('Error loading game:', error);
            this.dataStore.set('game', null);
        }
    }

    /**
     * Add the header to the page and load the TrueAchievementsGroupClient.
     */
    async mount() {
        await this.header.addHeaderToPage();
        this.client = new TrueAchievementsGroupClient();
        await this.clientLoaded();
    }

    /**
     * When the game is updated in the datastore, update the game metadata on the page.
     */
    addGameToPage() {
        try {
            const game = this.dataStore.get('game');

            document.getElementById('game-gameName').innerText = game.gameName || 'N/A';
            document.getElementById('game-platform').innerText = game.platform || 'N/A';
            document.getElementById('game-gameURL').innerText = game.gameURL || 'N/A';
            document.getElementById('game-achievementsWonNoDlc').innerText = game.achievementsWonNoDlc || 'N/A';
            document.getElementById('game-maxAchievementsNoDlc').innerText = game.maxAchievementsNoDlc || 'N/A';
            document.getElementById('game-achievementsWonIncludeDlc').innerText = game.achievementsWonIncludeDlc || 'N/A';
            document.getElementById('game-maxAchievementsIncludeDlc').innerText = game.maxAchievementsIncludeDlc || 'N/A';
            document.getElementById('game-gamerScoreWonNoDlc').innerText = game.gamerScoreWonNoDlc || 'N/A';
            document.getElementById('game-maxGamerScoreNoDlc').innerText = game.maxGamerScoreNoDlc || 'N/A';
            document.getElementById('game-gamerScoreWonIncludeDlc').innerText = game.gamerScoreWonIncludeDlc || 'N/A';
            document.getElementById('game-maxGamerScoreIncludeDlc').innerText = game.maxGamerScoreIncludeDlc || 'N/A';
            document.getElementById('game-trueAchievementWonNoDlc').innerText = game.trueAchievementWonNoDlc || 'N/A';
            document.getElementById('game-maxTrueAchievementNoDlc').innerText = game.maxTrueAchievementNoDlc || 'N/A';
            document.getElementById('game-trueAchievementWonIncludeDlc').innerText = game.trueAchievementWonIncludeDlc || 'N/A';
            document.getElementById('game-maxTrueAchievementIncludeDlc').innerText = game.maxTrueAchievementIncludeDlc || 'N/A';
            document.getElementById('game-myCompletionPercentage').innerText = game.myCompletionPercentage || 'N/A';
            document.getElementById('game-completionDate').innerText = game.completionDate || 'N/A';
            document.getElementById('game-challengesWon').innerText = game.challengesWon || 'N/A';
            document.getElementById('game-maxChallenges').innerText = game.maxChallenges || 'N/A';
            document.getElementById('game-hoursPlayed').innerText = game.hoursPlayed || 'N/A';
            document.getElementById('game-myRating').innerText = game.myRating || 'N/A';
            document.getElementById('game-siteRating').innerText = game.siteRating || 'N/A';
            document.getElementById('game-myRatio').innerText = game.myRatio || 'N/A';
            document.getElementById('game-siteRatio').innerText = game.siteRatio || 'N/A';
            document.getElementById('game-ownershipStatus').innerText = game.ownershipStatus || 'N/A';
            document.getElementById('game-playStatus').innerText = game.playStatus || 'N/A';
            document.getElementById('game-format').innerText = game.format || 'N/A';
            document.getElementById('game-completionEstimate').innerText = game.completionEstimate || 'N/A';
            document.getElementById('game-walkthrough').innerText = game.walkthrough || 'N/A';
            this.populateList('game-gameNotes', game.gameNotes);
            document.getElementById('game-contestStatus').innerText = game.contestStatus || 'N/A';

        } catch (error) {
            console.error('Error fetching game:', error);
            document.getElementById('game-name').innerText = "Error fetching game";
        }
    }

    /**
     * Populate a list in the HTML with the provided data.
     * @param {string} elementId - The ID of the HTML element to populate.
     * @param {Array<string>} data - The array of strings to display.
     */
    populateList(elementId, data) {
        try {
            const listElement = document.getElementById(elementId);

            // Clear existing content
            listElement.innerHTML = '';

            // Check if data is provided and is an array
            if (data && Array.isArray(data)) {
                const ul = document.createElement('ul');

                data.forEach((item) => {
                    const li = document.createElement('li');
                    li.textContent = item;
                    ul.appendChild(li);
                });

                listElement.appendChild(ul);
            } else {
                listElement.innerText = 'No notes available.';
            }
        } catch (error) {
            console.error('Error populating list:', error);
        }
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewGame = new ViewGame();
    await viewGame.mount();
};

window.addEventListener('DOMContentLoaded', main);
