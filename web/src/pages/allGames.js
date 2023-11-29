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
        this.bindClassMethods(['clientLoaded', 'mount', 'addGamesToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGamesToPage);
        this.header = new Header(this.dataStore);
        console.log("viewgames constructor");
    }

    /**
     * Once the client is loaded, get the game list.
     */
    async clientLoaded() {
        const games = await this.client.getAllGames();
        this.dataStore.set('games', games);
    }

    /**
     * Add the header to the page and load the TrueAchievementGroupClient.
     */
    mount() {
        this.header.addHeaderToPage();

        this.client = new TrueAchievementsGroupClient();
        this.clientLoaded();
    }

    /**
     * When the games list is updated in the datastore, update the games metadata on the page.
     */
    addGamesToPage() {
        console.log("addgamestopage")
        const games = this.dataStore.get('games');
        if (games == null) {
            console.log("null games");
            return;
        }

        const gamesList = document.getElementById("games-list");

        for(var i = 0; i < games.length; i++) {
            const game = games[i];
            const gameName = game.gameName || 'unknown'; // Replace null with an empty string
            const platform = game.consolePlatform || 'unknown'; // Replace null with an empty string
            const gameUrl = game.gameUrl || 'unknown'; // Replace null with an empty string
            const achievementsWonIncludeDlc = game.achievementsWonIncludeDlc || 'unknown'; // Replace null with an empty string
            const maxAchievementsIncludeDlc = game.maxAchievementsIncludeDlc || 'unknown'; // Replace null with an empty string
            const gamerscoreWonIncludeDlc = game.gamerscoreWonIncludeDlc || 'unknown'; // Replace null with an empty string
            const maxGamerscoreIncludeDlc = game.maxGamerscoreIncludeDlc || 'unknown'; // Replace null with an empty string
            const trueAchievementWonIncludeDlc = game.trueAchievementWonIncludeDlc || 'unknown'; // Replace null with an empty string
            const maxTrueAchievementIncludeDlc = game.maxTrueAchievementIncludeDlc || 'unknown'; // Replace null with an empty string
            const myCompletionPercentage = game.myCompletionPercentage || 'unknown'; // Replace null with an empty string
            const completionDate = game.completionDate || 'unknown'; // Replace null with an empty string
            const hoursPlayed = game.hoursPlayed || 'unknown'; // Replace null with an empty string
            const gameNotes = game.gameNotes || 'unknown'; // Replace null with an empty string

            // Create elements to display game information
            const gameElement = document.createElement('div');
            gameElement.className = 'game';

            const nameElement = document.createElement('h2');
            nameElement.textContent = `Game: ${gameName}`;

            const platformElement = document.createElement('p');
            platformElement.textContent = `Platform: ${platform}`;

            const gameUrlElement = document.createElement('p');
            gameUrlElement.textContent = `Game URL: ${gameUrl}`;

            const achievementsWonIncludeDlcElement = document.createElement('p');
            achievementsWonIncludeDlcElement.textContent = `Achievements Won Include Dlc: ${achievementsWonIncludeDlc}`;

            const maxAchievementsIncludeDlcElement = document.createElement('p');
            maxAchievementsIncludeDlcElement.textContent = `Max Achievements Include Dlc: ${maxAchievementsIncludeDlc}`;

            const gamerscoreWonIncludeDlcElement = document.createElement('p');
            gamerscoreWonIncludeDlcElement.textContent = `Gamerscore Won Include Dlc: ${gamerscoreWonIncludeDlc}`;

            const maxGamerscoreIncludeDlcElement = document.createElement('p');
            maxGamerscoreIncludeDlcElement.textContent = `Max Gamerscore Include Dlc: ${maxGamerscoreIncludeDlc}`;

            const trueAchievementWonIncludeDlcElement = document.createElement('p');
            trueAchievementWonIncludeDlcElement.textContent = `True Achievement Won Include Dlc: ${trueAchievementWonIncludeDlc}`;

            const maxTrueAchievementIncludeDlcElement = document.createElement('p');
            maxTrueAchievementIncludeDlcElement.textContent = `Max True Achievement Include Dlc: ${maxTrueAchievementIncludeDlc}`;

            const myCompletionPercentageElement = document.createElement('p');
            myCompletionPercentageElement.textContent = `My Completion Percentage: ${myCompletionPercentage}`;

            const completionDateElement = document.createElement('p');
            completionDateElement.textContent = `Completion Date: ${completionDate}`;

            const hoursPlayedElement = document.createElement('p');
            hoursPlayedElement.textContent = `Hours Played: ${hoursPlayed}`;

            const gameNotesElement = document.createElement('p');
            gameNotesElement.textContent = `Game Notes: ${gameNotes}`;

            // Add game information to the game element
            gameElement.appendChild(nameElement);
            gameElement.appendChild(platformElement);
            gameElement.appendChild(gameUrlElement);
            gameElement.appendChild(achievementsWonIncludeDlcElement);
            gameElement.appendChild(maxAchievementsIncludeDlcElement);
            gameElement.appendChild(gamerscoreWonIncludeDlcElement);
            gameElement.appendChild(maxGamerscoreIncludeDlcElement);
            gameElement.appendChild(trueAchievementWonIncludeDlcElement);
            gameElement.appendChild(maxTrueAchievementIncludeDlcElement);
            gameElement.appendChild(myCompletionPercentageElement);
            gameElement.appendChild(completionDateElement);
            gameElement.appendChild(hoursPlayedElement);
            gameElement.appendChild(gameNotesElement);

            // Add the game element to the page
            gamesList.appendChild(gameElement);
        }
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewGames = new ViewGames();
    viewGames.mount();
};

window.addEventListener('DOMContentLoaded', main);
