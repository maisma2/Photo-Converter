package org.remoteHandler;

import okhttp3.*;
import okhttp3.MediaType;

import java.io.IOException;


//Uses default OkHTTPHandler from https://square.github.io/okhttp/
public class OkHTTPHandler {
    private static final MediaType file = MediaType.get("application/octet-stream");
    private static final OkHttpClient client = new OkHttpClient();

    public String post(byte[] file, String fileName, String URL) throws IOException {
        RequestBody body = RequestBody.create(file);
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Accept", "application/json")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }
}
