package org.applicationRunner;

import com.google.api.client.auth.oauth2.BrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.models.OAuthModels;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class OAuthClient {
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/photoslibrary");
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    public static String getAuthorizationUrl() throws IOException {
        // Load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(OAuthClient.class.getResourceAsStream(OAuthModels.SECRET_PATH)));

        // Build the flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        // Redirect to the authorization URL
        String redirectUri = "http://localhost:8888/callback"; // This needs to match the redirect URI registered in Google Cloud Console
        return flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
    }

    // Method to handle callback and exchange code for tokens will go here
}
