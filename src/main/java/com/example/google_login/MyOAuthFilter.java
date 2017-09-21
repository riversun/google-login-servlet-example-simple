package com.example.google_login;

import java.util.Arrays;
import java.util.List;

import org.riversun.oauth2.google.OAuthFilter;

/**
 * OAuth Filter
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class MyOAuthFilter extends OAuthFilter {

    @Override
    protected String getAuthRedirectUrl() {
        return MyOAuthCallbackServlet.OAUTH2_CALLBACK_URL;
    }

    // Return is authenticate everytime
    @Override
    protected boolean isAuthenticateEverytime() {
        /**
         * <p>
         * If true, execute OAuth2-flow every time<br>
         * </P
         * <p>
         * If false,<br>
         * once access_token have been retrieved from OAuth2-flow that you will not need to get it unless you revoke it.<br>
         * or clear(forget) the OAuth2 state(oauth-passed state) <br>
         * so that the user will need to do OAuth-flow again the next time.<br>
         * {@link OAuthSession.getInstance().clearOAuth2State(req)}
         * </p>
         */
        return true;
    }

    // Return OAuth2 scope you want to be granted to by users
    @Override
    protected List<String> getScopes() {

        final String OAUTH2_SCOPE_MAIL = "email";
        final String OAUTH2_SCOPE_USERINFO_PROFILE = "https://www.googleapis.com/auth/userinfo.profile";

        return Arrays.asList(OAUTH2_SCOPE_MAIL, OAUTH2_SCOPE_USERINFO_PROFILE);

    }

}
