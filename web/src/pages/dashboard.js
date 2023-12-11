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
        this.bindClassMethods(['clientLoaded', 'mount', 'updateUserScoreboard'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.updateUserScoreboard);
        this.header = new Header(this.dataStore);
        console.log('Dashboard constructor');
    }

    /**
     * Once the client is loaded, get the user information.
     */
    async clientLoaded() {
        try {
            console.log('Retrieving data from the backend');
            const userData = await this.client.getUserStats();
            console.log('Retrieved data from the backend', userData);

            // Store the user data in the data store
            this.dataStore.set('userData', {
                gamerScoreWonIncludeDlc: userData.gamerScoreWonIncludeDlc || 0, // replace 0 with a default value if needed
                trueAchievementWonIncludeDlc: userData.trueAchievementWonIncludeDlc || 0,
                myCompletionPercentage: userData.myCompletionPercentage || 0,
            });
        } catch (error) {
            console.error('Error fetching user information:', error);
        }
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
     * When the user data is updated in the datastore, update the scoreboard on the page.
     */
    updateUserScoreboard() {
        console.log('updateUserScoreboard');
        const userData = this.dataStore.get('userData');
        if (userData == null) {
            console.log('null userData');
            return;
        }


        const gamerScoreElement = document.getElementById('gamer-score');
        const achievementsWonElement = document.getElementById('achievements-won');
        const completionPercentageElement = document.getElementById('completion-percentage');

        // Update the elements with user data
        gamerScoreElement.textContent = `GamerScore: ${userData.gamerScoreWonIncludeDlc}`;
        achievementsWonElement.textContent = `TrueAchievementsWon: ${userData.trueAchievementWonIncludeDlc}`;
        completionPercentageElement.textContent = `Completion Percentage: ${userData.myCompletionPercentage}`;
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
