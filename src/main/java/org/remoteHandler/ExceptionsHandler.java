package org.remoteHandler;

public class ExceptionsHandler {
    public static void handleException(Exception e) {
        System.out.println("Exception: " + e.getMessage());
    }
}
