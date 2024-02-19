package org.util;


import io.github.cdimascio.dotenv.*;

public class APIConfigUtil {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String getConvertAPIKey() { return dotenv.get("CONVERT_API_KEY_ENV"); }
    public static String getConvertAPISecret() { return dotenv.get("CONVERT_API_SECRET_ENV"); }
    public static String getOAuthClientID() { return dotenv.get("GOOGLE_OAUTH_CLIENT_ID_ENV"); }
    public static String getOAuthClientSecret(){ return dotenv.get("GOOGLE_OAUTH_CLIENT_SECRET_ENV");}
    public static String getOAuthSecretPath()  { return dotenv.get("GOOGLE_OAUTH_SECRET_PATH_ENV");}

}
