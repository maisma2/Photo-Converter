package org.models;
import org.util.APIConfigUtil;

public class OAuthModels {
    private static final APIConfigUtil apiConfig = new APIConfigUtil();
    public static final String CLIENT_ID = apiConfig.getOAuthClientID();
    public static final String CLIENT_SECRET = apiConfig.getOAuthClientSecret();
    public static final String SECRET_PATH = apiConfig.getOAuthSecretPath();




}
