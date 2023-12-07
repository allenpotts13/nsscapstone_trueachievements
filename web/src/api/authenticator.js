import BindingClass from "../util/bindingClass";
import { Auth } from 'aws-amplify';

export default class Authenticator extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['getCurrentUserInfo', 'refreshTokens', 'isUserLoggedIn', 'getUserToken', 'login', 'logout'];
        this.bindClassMethods(methodsToBind, this);

        this.configureCognito();
    }

    async getCurrentUserInfo() {
        await this.refreshTokens();
        try {
            const cognitoUser = await Auth.currentAuthenticatedUser();
            const { email, name } = cognitoUser.signInUserSession.idToken.payload;
            return { email, name };
        } catch (error) {
            console.error('Failed to get current user info:', error);
            throw error;
        }

    }

    async refreshTokens() {
        try {
            await Auth.currentSession();
        } catch (error) {
            console.error('Token refresh failed:', error);
            throw error;
        }
    }


    async isUserLoggedIn() {
        try {
            await Auth.currentAuthenticatedUser();
            return true;
        } catch {
            return false;
        }
    }

    async getUserToken() {
        const cognitoSession = await Auth.currentSession();
        return cognitoSession.getIdToken().getJwtToken();
    }

    async login() {
        await Auth.federatedSignIn();
    }

    async logout() {
        await Auth.signOut();
    }

    configureCognito() {
        Auth.configure({
            userPoolId: process.env.COGNITO_USER_POOL_ID,
            userPoolWebClientId: process.env.COGNITO_USER_POOL_CLIENT_ID,
            oauth: {
                domain: process.env.COGNITO_DOMAIN,
                redirectSignIn: process.env.COGNITO_REDIRECT_SIGNIN,
                redirectSignOut: process.env.COGNITO_REDIRECT_SIGNOUT,
                region: 'us-east-2',
                scope: ['email', 'openid', 'phone', 'profile'],
                responseType: 'code'
            }
        });
    }
}
