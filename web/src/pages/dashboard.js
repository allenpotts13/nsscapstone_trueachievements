import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the dashboard page of the website.
 */
class Dashboard extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'updateUserScoreboard', 'updateButtonVisibility'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.updateUserScoreboard);
        this.header = new Header(this.dataStore);
    }

    /**
     * Once the client is loaded, get the user information.
     */
    async clientLoaded() {
        try {
            // Check if the user is logged in
            if (await this.client.authenticator.isUserLoggedIn()) {
                const userData = await this.client.getUserStats();

            // Store the user data in the data store
            this.dataStore.set('userData', {
                gamerScoreWonIncludeDlc: userData.gamerScoreWonIncludeDlc || 0,
                trueAchievementWonIncludeDlc: userData.trueAchievementWonIncludeDlc || 0,
                myCompletionPercentage: userData.myCompletionPercentage || 0,
            });

            await this.updateButtonVisibility();
        } else {
            console.log('User is not logged in');
        }
        } catch (error) {
            console.error('Error fetching user information:', error);
        }
    }

    /**
     * Add the header to the page and load the TrueAchievementGroupClient.
     */
    mount() {
        this.client = new TrueAchievementsGroupClient();
        //Check if the user is logged in
        if (this.client.authenticator.isUserLoggedIn()) {
            this.header.addHeaderToPage();
            this.clientLoaded();
            this.updateButtonVisibility();
        } else {
            console.log('User is not logged in');
        }
    }

    /**
     * When the user data is updated in the datastore, update the scoreboard on the page.
     */
    updateUserScoreboard() {
        const userData = this.dataStore.get('userData');
        const gamerScoreElement = document.getElementById('gamer-score');
        const achievementsWonElement = document.getElementById('achievements-won');
        const completionPercentageElement = document.getElementById('completion-percentage');
        if (userData == null) {
            console.log('null userData');
            gamerScoreElement.textContent = 'GamerScore: Loading...';
            achievementsWonElement.textContent = 'TrueAchievementsWon: Loading...';
            completionPercentageElement.textContent = 'Completion Percentage: Loading...';
            return;
        }

        // Update the elements with user data
        gamerScoreElement.textContent = `GamerScore: ${userData.gamerScoreWonIncludeDlc}`;
        achievementsWonElement.textContent = `TrueAchievementsWon: ${userData.trueAchievementWonIncludeDlc}`;
        completionPercentageElement.textContent = `Completion Percentage: ${userData.myCompletionPercentage}`;
    }

    async updateButtonVisibility() {
        try {
            const userIsLoggedIn = await this.client.authenticator.isUserLoggedIn();
            const groupsButton = document.getElementById('groups-button');
            const gameLibraryButton = document.getElementById('game-library-button');

            if (groupsButton && gameLibraryButton) {
                groupsButton.style.display = userIsLoggedIn ? 'inline-block' : 'none';
                gameLibraryButton.style.display = userIsLoggedIn ? 'inline-block' : 'none';
            }
        } catch (error) {
            console.error('Error checking user login status:', error);
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const dashboard = new Dashboard();
    dashboard.mount();
};

window.addEventListener('DOMContentLoaded', main);
