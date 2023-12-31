import TrueAchievementsGroupClient from '../api/TrueAchievementsGroupClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton', 'createGoBackButton'
        ];
        this.bindClassMethods(methodsToBind, this);
        this.client = new TrueAchievementsGroupClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        const userInfo = this.createUserInfoForHeader(currentUser);
        const goBackButton = this.createGoBackButton();

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        header.appendChild(userInfo);
        header.appendChild(goBackButton);
    }

    createSiteTitle() {
        // Create a Home button
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'Home';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createGoBackButton() {
        const goBackButton = document.createElement('button');
        goBackButton.classList.add('button');
        goBackButton.innerText = 'Go Back';

        goBackButton.addEventListener('click', () => {
            console.log('Go Back button clicked!');
            window.history.back();
        });

        return goBackButton;
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}
