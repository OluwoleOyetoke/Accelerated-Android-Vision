package com.oluwoleoyetoke.acceleratedvision;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class StreamActivity extends AppCompatActivity {
    public int toggle=0; //to monitor ON/OFF
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(getBaseContext(),getString(R.string.stream_stoped),Toast.LENGTH_SHORT).show();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle==0) {
                   toggle=1;
                    fab.setImageResource(android.R.drawable.button_onoff_indicator_on);
                    Toast.makeText(getBaseContext(),getString(R.string.stream_started),Toast.LENGTH_SHORT).show();
                }else if(toggle==1){
                    toggle=0;
                    fab.setImageResource(android.R.drawable.button_onoff_indicator_off);
                    Toast.makeText(getBaseContext(),getString(R.string.stream_stoped),Toast.LENGTH_SHORT).show();
                }


                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
