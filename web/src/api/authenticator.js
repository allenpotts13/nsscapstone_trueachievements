import BindingClass from "../util/bindingClass";
import { Amplify } from "aws-amplify";
import {
    fetchAuthSession,
    getCurrentUser,
    signInWithRedirect,
    signOut
} from "aws-amplify/auth";

export default class Authenticator extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'getCurrentUserInfo',
            'refreshTokens',
            'isUserLoggedIn',
            'getUserToken',
            'login',
            'logout'
        ];

        this.bindClassMethods(methodsToBind, this);

        this.configureCognito();
    }

    async getCurrentUserInfo() {
        await this.refreshTokens();

        try {
            const session = await fetchAuthSession();

            const email = session.tokens?.idToken?.payload?.email;
            const name = session.tokens?.idToken?.payload?.name;

            return { email, name };
        } catch (error) {
            console.error('Failed to get current user info:', error);
            throw error;
        }
    }

    async refreshTokens() {
        try {
            await fetchAuthSession();
        } catch (error) {
            console.error('Token refresh failed:', error);
            throw error;
        }
    }

    async isUserLoggedIn() {
        try {
            await getCurrentUser();
            return true;
        } catch {
            return false;
        }
    }

    async getUserToken() {
        const session = await fetchAuthSession();
        return session.tokens?.idToken?.toString();
    }

    async login() {
        await signInWithRedirect();
    }

    async logout() {
        await signOut();
    }

    configureCognito() {
        Amplify.configure({
            Auth: {
                Cognito: {
                    userPoolId: process.env.COGNITO_USER_POOL_ID,
                    userPoolClientId: process.env.COGNITO_USER_POOL_CLIENT_ID,
                    loginWith: {
                        oauth: {
                            domain: process.env.COGNITO_DOMAIN,
                            scopes: ['email', 'openid', 'phone', 'profile'],
                            redirectSignIn: [
                                process.env.COGNITO_REDIRECT_SIGNIN
                            ],
                            redirectSignOut: [
                                process.env.COGNITO_REDIRECT_SIGNOUT
                            ],
                            responseType: 'code'
                        }
                    }
                }
            }
        });
    }
}