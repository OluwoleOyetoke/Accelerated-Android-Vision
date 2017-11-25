package com.oluwoleoyetoke.acceleratedvision;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Intent.ACTION_VIEW;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //Inflate Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //When the collaborate button is clicked
    public void onClickCollaborateButton(View view){
        Button collaborateButton = (Button) view;
         if(collaborateButton.getId()==R.id.about_button1){
            Uri uri = Uri.parse(getString(R.string.project_git_hub));
            Intent collaborationIntent = new Intent(ACTION_VIEW, uri); //Use web browser to visit project Git Page
            //Make sure package exists
            if(collaborationIntent.resolveActivity(getPackageManager())!=null){
                startActivity(collaborationIntent);
            }
        }
    }

    //Go to playstore
    public void onClickPlayStore(View view) {
        TextView playStoreView = (TextView) view;
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        if (playStoreView.getId() == R.id.aboutTextView5) {
            Uri uri1 = Uri.parse("market://details?id=" + appPackageName);
            Uri uri2 = Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName);

            try {
                Intent playStoreIntent = new Intent(ACTION_VIEW, uri1); //Use playstore
                if (playStoreIntent.resolveActivity(getPackageManager()) != null) {         //Make sure package exists
                    startActivity(playStoreIntent);
                }
            } catch (android.content.ActivityNotFoundException anfe) {
                Intent playStoreIntent = new Intent(ACTION_VIEW, uri2); //Use web browser.
                if (playStoreIntent.resolveActivity(getPackageManager()) != null) {         //Make sure package exists
                    startActivity(playStoreIntent);
                }
            }
        }
    }


    //Add menu
    //When any of the menu is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem) {
        if(menuitem.getItemId()==R.id.about_options_id && this.getClass()!=AboutActivity.class){
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);//Go to the about activity page
        }else if(menuitem.getItemId()==R.id.collaborate_options_id){
            Uri uri = Uri.parse(getString(R.string.project_git_hub));
            Intent collaborationIntent = new Intent(ACTION_VIEW, uri); //Use web browser to visit project Git Page
            //Make sure package exists
            if(collaborationIntent.resolveActivity(getPackageManager())!=null){
                startActivity(collaborationIntent);
            }
        }else if(menuitem.getItemId()==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return true;
    }


}
