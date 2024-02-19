package org.applicationRunner;

import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.util.APIConfigUtil;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;

public class OAuthClient {
    public static void main(String[] args) throws Exception {
        OAuthClient client = new OAuthClient();
        client.authorize();
    }

    public void authorize() throws Exception {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                GsonFactory.getDefaultInstance(),
                new InputStreamReader(new FileInputStream(APIConfigUtil.getOAuthSecretPath())));

        // Set up authorization code flow.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), GsonFactory.getDefaultInstance(), clientSecrets,
                Collections.singletonList("https://www.googleapis.com/auth/drive"))
                .setAccessType("offline")
                .build();

        // Authorize.
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        new com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver.Builder().setPort(8888).build();
        com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp ab =
                new com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp(flow, receiver);
        ab.authorize("user");

        System.out.println("OAuth2 Authorization Flow Completed.");
    }
}
