package org.remoteHandler;


import org.util.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import okhttp3.*;
//Handles ConvertAPI through HTTPS requests
public class ConvertRESTFulCaller {
    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey = "your-api-secret"; // Replace with your actual API key

    public void convertFileJPGtoPNG(Logger logger, String sourcePath, String destinationPath) throws IOException {
        // Encode the source file to Base64
        byte[] fileContent = Files.readAllBytes(Path.of(sourcePath));
        String encodedFile = Base64.getEncoder().encodeToString(fileContent);

        // Construct the JSON request body
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = """
                {
                    "Parameters": [
                        {
                            "Name": "File",
                            "FileValue": {
                                "Name": "my_file.jpg",
                                "Data": "%s"
                            }
                        },
                        {
                            "Name": "StoreFile",
                            "Value": true
                        }
                    ]
                }
                """.formatted(encodedFile);

        // Make the POST request
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("https://v2.convertapi.com/convert/jpg/to/png?Secret=" + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                logger.log("File converted successfully. Response: " + responseBody);
                // Extract the URL to the converted file from the response
                // and handle the file download or further processing as needed.
            } else {
                logger.log("File conversion failed. Response: " + (response.body() != null ? response.body().string() : "No response body"));
            }
        } catch (Exception e) {
            logger.log("Exception during file conversion: " + e.getMessage());
            throw e;
        }
    }
}
