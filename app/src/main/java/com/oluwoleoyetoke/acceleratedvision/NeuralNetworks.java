package com.oluwoleoyetoke.acceleratedvision;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Intent.ACTION_VIEW;

/**
 * Created by Oluwole_Jnr on 21/11/2017.
 */

public class NeuralNetworks {

    //Declare needed variables
    private long downloadReference=-1;



    //Function to help download trained NN into mobile device local storage
    public void getTrainedNetwork(TextView textview, Context context){
        //Create URI
        Uri uri = Uri.parse(context.getString(R.string.network_address));

        //Make App storage folder, if not exist
        File dir = new File(Environment.getExternalStorageDirectory() + context.getString(R.string.local_network_directory));
        dir.mkdirs(); // creates needed dirs

        //Pass URI to download manager
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //Direct download to save into user's 'Document' folder
        //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, "Accelerated/Network.lite");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, context.getString(R.string.local_network_directory2));

        // Allow media devices to scan through it
        request.allowScanningByMediaScanner();

        //Set title of request, description and notify user when download is completed
        request.setTitle(context.getString(R.string.download_title));
        request.setDescription(context.getString(R.string.download_description));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        // Start download & notify user
        DownloadManager dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        downloadReference = dm.enqueue(request);
        Toast.makeText(context, R.string.download_started, Toast.LENGTH_SHORT).show();

        //To notify user when download is completed
        Cursor c = dm.query(new DownloadManager.Query().setFilterByStatus(DownloadManager.STATUS_PAUSED
                | DownloadManager.STATUS_PENDING
                | DownloadManager.STATUS_RUNNING));

        BroadcastReceiver onComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, R.string.download_completed, Toast.LENGTH_LONG).show();
            }
        };
        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }


    //Function used to check progress of NN downlaod
    public void checkNNDownloadStatus(Context context){
        DownloadManager.Query query = new DownloadManager.Query();
        if(downloadReference!=-1) {
            query.setFilterById(downloadReference);
            //Query the download manager about downloads that have been requested.
            DownloadManager dm = (DownloadManager)context.getSystemService(DOWNLOAD_SERVICE);
            Cursor cursor = dm.query(query);
            if(cursor.moveToFirst()){
                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS); //column for download  status
                int status = cursor.getInt(columnIndex);
                int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON); //column for reason id (if the download failed or paused)
                int reason = cursor.getInt(columnReason);
                int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME); //get the download filename
                String filename = cursor.getString(filenameIndex);

                String statusText = "";
                String reasonText = "";
                switch(status){
                    case DownloadManager.STATUS_FAILED:
                        statusText = "STATUS_FAILED";
                        switch(reason){
                            case DownloadManager.ERROR_CANNOT_RESUME:
                                reasonText = "ERROR_CANNOT_RESUME";
                                break;
                            case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                                reasonText = "ERROR_DEVICE_NOT_FOUND";
                                break;
                            case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                                reasonText = "ERROR_FILE_ALREADY_EXISTS";
                                break;
                            case DownloadManager.ERROR_FILE_ERROR:
                                reasonText = "ERROR_FILE_ERROR";
                                break;
                            case DownloadManager.ERROR_HTTP_DATA_ERROR:
                                reasonText = "ERROR_HTTP_DATA_ERROR";
                                break;
                            case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                                reasonText = "ERROR_INSUFFICIENT_SPACE";
                                break;
                            case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                                reasonText = "ERROR_TOO_MANY_REDIRECTS";
                                break;
                            case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                                reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                                break;
                            case DownloadManager.ERROR_UNKNOWN:
                                reasonText = "ERROR_UNKNOWN";
                                break;
                        }
                        break;
                    case DownloadManager.STATUS_PAUSED:
                        statusText = "STATUS_PAUSED";
                        switch(reason){
                            case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                                reasonText = "PAUSED_QUEUED_FOR_WIFI";
                                break;
                            case DownloadManager.PAUSED_UNKNOWN:
                                reasonText = "PAUSED_UNKNOWN";
                                break;
                            case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                                reasonText = "PAUSED_WAITING_FOR_NETWORK";
                                break;
                            case DownloadManager.PAUSED_WAITING_TO_RETRY:
                                reasonText = "PAUSED_WAITING_TO_RETRY";
                                break;
                        }
                        break;
                    case DownloadManager.STATUS_PENDING:
                        statusText = "STATUS_PENDING";
                        break;
                    case DownloadManager.STATUS_RUNNING:
                        statusText = "STATUS_RUNNING";
                        break;
                    case DownloadManager.STATUS_SUCCESSFUL:
                        statusText = "STATUS_SUCCESSFUL";
                        reasonText = "Filename:\n" + filename;
                        break;
                }
                //Show download status message
                Toast.makeText(context, "Download Status: " + "\n" + statusText + "\n" + reasonText, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, R.string.no_download, Toast.LENGTH_SHORT).show(); //Show Download Status Message
        }
    }
}
