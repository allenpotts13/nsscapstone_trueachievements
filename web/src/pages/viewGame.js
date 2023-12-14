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

            // Initialize the client instance
            this.client = new TrueAchievementsGroupClient();

            if (!this.client) {
                console.error('Error: TrueAchievementsGroupClient is not initialized.');
                return;
            }

            document.getElementById('game-gameName').innerText = 'Loading game ...';
            const game = await this.client.getGame(uniqueId);
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

            const setInnerText = (elementId, value) => {
                const element = document.getElementById(elementId);
                if (element) {
                    element.innerText = value || 'N/A';
                }
            };

            setInnerText('game-gameName', game.gameName);
            setInnerText('game-platform', game.platform);
            const gameUrlElement = document.getElementById('gameUrlLink');
            if (gameUrlElement) {
                if (game.gameURL) {
                    const link = document.createElement('a');
                    link.href = game.gameURL;
                    link.target = '_blank';
                    link.textContent = game.gameURL;
                    gameUrlElement.innerHTML = ''; // Clear existing content
                    gameUrlElement.appendChild(link);
                } else {
                    gameUrlElement.innerHTML = 'N/A';
                }
            }
            setInnerText('game-achievementsWonNoDlc', game.achievementsWonNoDlc);
            setInnerText('game-maxAchievementsNoDlc', game.maxAchievementsNoDlc);
            setInnerText('game-achievementsWonIncludeDlc', game.achievementsWonIncludeDlc);
            setInnerText('game-maxAchievementsIncludeDlc', game.maxAchievementsIncludeDlc);
            setInnerText('game-gamerScoreWonNoDlc', game.gamerScoreWonNoDlc);
            setInnerText('game-maxGamerScoreNoDlc', game.maxGamerScoreNoDlc);
            setInnerText('game-gamerScoreWonIncludeDlc', game.gamerScoreWonIncludeDlc);
            setInnerText('game-maxGamerScoreIncludeDlc', game.maxGamerScoreIncludeDlc);
            setInnerText('game-trueAchievementWonNoDlc', game.trueAchievementWonNoDlc);
            setInnerText('game-maxTrueAchievementNoDlc', game.maxTrueAchievementNoDlc);
            setInnerText('game-trueAchievementWonIncludeDlc', game.trueAchievementWonIncludeDlc);
            setInnerText('game-maxTrueAchievementIncludeDlc', game.maxTrueAchievementIncludeDlc);
            setInnerText('game-myCompletionPercentage', game.myCompletionPercentage);
            setInnerText('game-completionDate', game.completionDate);
            setInnerText('game-challengesWon', game.challengesWon);
            setInnerText('game-maxChallenges', game.maxChallenges);
            setInnerText('game-hoursPlayed', game.hoursPlayed);
            setInnerText('game-myRating', game.myRating);
            setInnerText('game-siteRating', game.siteRating);
            setInnerText('game-myRatio', game.myRatio);
            setInnerText('game-siteRatio', game.siteRatio);
            setInnerText('game-ownershipStatus', game.ownershipStatus);
            setInnerText('game-playStatus', game.playStatus);
            setInnerText('game-format', game.format);
            setInnerText('game-completionEstimate', game.completionEstimate);
            const walkthroughElement = document.getElementById('walkthroughLink');
            if (walkthroughElement) {
                if (game.walkthrough) {
                    const link = document.createElement('a');
                    link.href = game.walkthrough;
                    link.target = '_blank';
                    link.textContent = game.walkthrough;
                    walkthroughElement.innerHTML = ''; // Clear existing content
                    walkthroughElement.appendChild(link);
                } else {
                    walkthroughElement.innerHTML = 'N/A';
                }
            }
            setInnerText('game-contestStatus', game.contestStatus);
            this.populateList('game-gameNotes', game.gameNotes);


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
