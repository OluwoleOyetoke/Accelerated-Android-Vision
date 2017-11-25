package com.oluwoleoyetoke.acceleratedvision;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {
Controller control = new Controller();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Inflate Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //When any of the menu is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem) {
        if(menuitem.getItemId()==R.id.about_options_id){
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);//Go to the about activity page
        }else if(menuitem.getItemId()==R.id.collaborate_options_id){
            Uri uri = Uri.parse(getString(R.string.project_git_hub));
            Intent collaborationIntent = new Intent(ACTION_VIEW, uri); //Use web browser to visit project Git Page
            //Make sure package exists
            if(collaborationIntent.resolveActivity(getPackageManager())!=null){
                startActivity(collaborationIntent);
            }
        }
        return true;
    }

    //Download Trained Network
    public void onClickDownloadTrainedNet(View view){
        //Instantiate needed objects
        final TextView textview = (TextView) view;
        final NeuralNetworks NN = new NeuralNetworks();

        //Check if network already exist on device
        boolean networkFileExists=false;
        String appNetworkStorage = Environment.getExternalStorageDirectory() + getString(R.string.local_network_directory);
        boolean folderExists = control.checkIfFolderExists(appNetworkStorage);
        if(folderExists){
            boolean filesExist = control.checkIfFileExists(appNetworkStorage);
            if(filesExist){
                networkFileExists = control.checkIfNetworkFileExists(appNetworkStorage); //Check if file is Network.tfls
            }
        }

        if(networkFileExists){ //if network file already exists
            CharSequence choices[] = new CharSequence[] {"Yes", "No"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Network already exists. Redownload?");
            builder.setItems(choices, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which==0){
                        NN.getTrainedNetwork(textview, getBaseContext());//Redownload the network
                        Toast.makeText(getBaseContext(), getString(R.string.download_started), Toast.LENGTH_LONG); //Soft alert user
                    }else{
                       //Do Nothing
                    }
                }
            });
            builder.show();
        }else {
            NN.getTrainedNetwork(textview, this); //Download the network
            Toast.makeText(this, R.string.download_started, Toast.LENGTH_LONG); //Soft alert user
        }
    }

    //To move to the stream activity view
    public void onClickStartButton(View view){
        Controller control = new Controller();
        //Check if network already exist on device
        boolean networkFileExists=false;
        String appNetworkStorage = Environment.getExternalStorageDirectory() + getString(R.string.local_network_directory);
        boolean folderExists = control.checkIfFolderExists(appNetworkStorage);
        if(folderExists){
            boolean filesExist = control.checkIfFileExists(appNetworkStorage);
            if(filesExist){
                networkFileExists = control.checkIfNetworkFileExists(appNetworkStorage); //Check if file is Network.tfls
            }
        }
        if(networkFileExists==false){
            Toast.makeText(this, getString(R.string.network_not_exist), Toast.LENGTH_SHORT);
            return;
        }
        Button buttonView = (Button) view;
        Intent streamIntent = new Intent(this, StreamActivity.class);
        startActivity(streamIntent);
    }
}
