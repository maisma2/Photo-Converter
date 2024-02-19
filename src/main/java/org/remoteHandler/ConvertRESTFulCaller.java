package org.remoteHandler;


import org.util.Logger;

import java.io.IOException;

//Handles ConvertAPI through HTTPS requests
public class ConvertRESTFulCaller {
    public void ConvertFileJPG(Logger logger, String sourcePath, String destinationPath) throws IOException {
        try {
            logger.log("Converting file " + sourcePath + " to JPG");
        }
        catch (Exception e) {
            logger.log("File not converted");
        }


}
