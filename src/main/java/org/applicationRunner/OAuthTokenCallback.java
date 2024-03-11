package org.applicationRunner;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import java.io.IOException;

public class OAuthTokenCallback {
    private GoogleAuthorizationCodeFlow flow;

    public OAuthTokenCallback(){
        this.flow = null;
    }

    public void OAuthCallbackHandler(GoogleAuthorizationCodeFlow flow) {
        this.flow = flow;
    }

    public GoogleTokenResponse exchangeCodeForTokens(String code) throws IOException {
        // This needs to match the redirect URI used for generating the authorization URL
        String redirectUri = "http://localhost:8888/callback";

        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                .setRedirectUri(redirectUri)
                .execute();

        // Here you can use the tokenResponse to get the access token and optionally the refresh token
        // For example: tokenResponse.getAccessToken();
        // And then store them securely

        return tokenResponse;
    }
}
