import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create group page of the website.
 */
class CreateGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewGroup'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewGroup);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the TrueAchievementsGroupClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new TrueAchievementsGroupClient();
    }

    /**
     * Method to run when the create group submit button is pressed. Call the TrueAchievementsGroupService to create the
     * group.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const groupName = document.getElementById('group-name').value;

        const group = await this.client.createGroup(groupName, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('group', group);
    }

    /**
     * When the group is updated in the datastore, redirect to the view group page.
     */
    redirectToViewGroup() {
        const group = this.dataStore.get('group');
        if (group != null) {
            const encodedGroupName = encodeURIComponent(group.groupName);
            window.location.href = `/group.html?groupName=${encodedGroupName}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createGroup = new CreateGroup();
    createGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);
