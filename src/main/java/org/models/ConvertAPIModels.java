package org.models;

import okhttp3.*;
import org.util.APIConfigUtil;

import org.remoteHandler.OkHTTPHandler;
import org.util.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertAPIModels {

    private static final APIConfigUtil apiConfig = new APIConfigUtil();
    private static final OkHTTPHandler httpRequestSender = new OkHTTPHandler();

    //First we turn the file into raw file data
    private byte[] FileToRaw(String filePath) {
            try {
                Path path = Paths.get(filePath);
                byte[] data = Files.readAllBytes(path);
                return data;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    public boolean checkForXToY(Logger logger, String input, String output){
        //TODO implement the ConvertAPI staus code check if the conversion is supported, later on implement a way to decline any from accessing Conversion
        return true;
    }

    public boolean ConvertXToY(Logger logger, File image, String destination, String inputFormat, String outputFormat) throws IOException {
        byte[] data = FileToRaw(image.getCanonicalPath());
        String URL = "https://v2.convertapi.com/convert/" + inputFormat + "/to/" + outputFormat + "?secret=" + apiConfig.getConvertAPIKey();
        httpRequestSender.post(data, image.getName(), URL);
        return false;
    }


}
