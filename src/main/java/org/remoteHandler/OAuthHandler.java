package org.remoteHandler;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.*;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class OAuthHandler {
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"; //Auth0 redirect URI for local programs

    public OAuthHandler(){
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JsonFactory.ge(),
                new InputStreamReader(GoogleOAuthClient.class.getResourceAsStream("/path_to_credentials.json"))
        );

        // Build the flow and trigger user authorization request
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Arrays.asList("YOUR_REQUIRED_SCOPES") // e.g., "https://www.googleapis.com/auth/photoslibrary"
        )
                .setAccessType("offline")
                .build();

        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in a browser:");
        System.out.println(authorizationUrl);

        // Read authorization code from the standard input
        System.out.println("Enter the authorization code:");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        scanner.close();

        // Exchange the authorization code for an access token and a refresh token
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

        // Credential can be used to make authenticated requests
        Credential credential = flow.createAndStoreCredential(tokenResponse, null);

        System.out.println("Access Token: " + credential.getAccessToken());
        System.out.println("Refresh Token: " + credential.getRefreshToken());
    }
    }

}
