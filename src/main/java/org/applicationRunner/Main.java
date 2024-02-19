package org.applicationRunner;

import okhttp3.*;
//import com.fasterxml.jackson.*;
//import com.google.api.*;

import java.io.IOException;
import java.util.Scanner;

import org.remoteHandler.ConvertSDKConversionCaller;
import org.util.*;

import okhttp3.*;

//Handling HEIC to JPG conversion
//Uses Google Photo's API and OAuth Clients for image upload for easy batching
//Uses ConvertAPI to convert the files, using the REST api over SDK because this is a learning project
//Downloads the ConvertAPI into a destination folder

public class Main {
    public static void main(String[] args){
        try{
            final Logger logger = new Logger();
            final ConvertSDKConversionCaller convertSDKConversionCaller = new ConvertSDKConversionCaller();
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the source path: ");
            String sourcePath = in.nextLine();
            System.out.println("Enter the destination path: ");
            String destinationPath = in.nextLine();
            FileHandling fileHandling = new FileHandling(sourcePath, destinationPath, logger);
            logger.log("FileHandling object created with source " + fileHandling.getSourcePath() + " and destination " + fileHandling.getDestinationPath());
            logger.log("Starting conversion process");
            convertSDKConversionCaller.ConvertFileJPG(logger, fileHandling.getNextFile(), destinationPath);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}