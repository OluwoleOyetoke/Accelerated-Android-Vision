package com.oluwoleoyetoke.acceleratedvision;

import android.os.Environment;

import java.io.File;

/**
 * Created by Oluwole_Jnr on 21/11/2017.
 */

public class Controller {

    //Check if a directory exist
    public boolean checkIfFolderExists(String folderPath){
        File handle = new File(folderPath);
        if(handle.isDirectory()) {
           return true;
        }else {
            return false;
        }
    }

    //Check if the app storage directory contains a file
    public boolean checkIfFileExists(String folderPath){
        File directory = new File(folderPath);
        File[] contents = directory.listFiles();
        //the directory file is not really a directory..
        if (contents == null) {
            return false;
        }
        //Folder is empty
        else if (contents.length == 0) {
            return false;
        }
        //Folder contains files
        else {
            return true;
        }
    }

    //Check if the app storage directory contains trained Network
    public boolean checkIfNetworkFileExists(String folderPath){ //Only send confirmed directory to this method
        File directory = new File(folderPath);
        File[] contents = directory.listFiles();
        String fileName;
        boolean result=false;
        for(int i=0; i<contents.length; i++){
            fileName = contents[i].getName();
            if(fileName.contains("Network.tfl")){ //If file with the name Network.tfl exist
                result = true;
                break; //exit for loop
            }
        }
        return result;
    }


}
