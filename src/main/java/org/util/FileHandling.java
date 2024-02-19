package org.util;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;


public class FileHandling {
    private String sourcePath;
    private String destinationPath;
    protected Queue<File> files;

    //Everything is done with the call of the constructor
    public FileHandling(String sourcePath, String destinationPath, Logger logger) throws Exception {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.files = new ArrayDeque<File>();
        if(addFiles()) logger.log("Added " + files.size() + " files to the queue");
    }
    private void findHeicFiles(File folder, Queue<File> files) {
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) return;

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".heic")) {
                files.add(file);
            }
            else if (file.isDirectory()) {
                findHeicFiles(file, files);
            }
        }
    }


    private boolean addFiles() {
        //Check all files in the sourcePath for all .heic files and add them to the queue
        File sourceFolder = new File(sourcePath);
        if (!sourceFolder.isDirectory()) {
            return false;
        }

        findHeicFiles(sourceFolder, files);  // Start the recursive search from the root directory

        return true;
    }
    public boolean queueHasFiles(){
        return !files.isEmpty();
    }
    public File getNextFile(){return files.poll();}

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }
}
