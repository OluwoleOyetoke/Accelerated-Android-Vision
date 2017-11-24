package com.oluwoleoyetoke.acceleratedvision;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class StreamActivity extends AppCompatActivity {
    public int toggle=0; //to monitor ON/OFF
    private Camera mCamera = null;
    private CameraView mCameraView = null;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        Toast.makeText(getBaseContext(),getString(R.string.stream_stoped),Toast.LENGTH_SHORT).show();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle==0) {
                   toggle=1;
                    fab.setImageResource(android.R.drawable.button_onoff_indicator_on);
                    try{
                        mCamera = Camera.open();//you can use open(int) to use different cameras
                    } catch (Exception e){
                        Log.d("CAMERA ERROR", "Failed to get camera: " + e.getMessage());
                    }

                  /*  if(mCamera != null) {
                        mCameraView = new CameraView(getBaseContext(), mCamera);//create a SurfaceView to show camera data
                        R.id.streamSurfaceView.
                        FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
                        camera_view.addView(mCameraView);//add the SurfaceView to the layout
                    }*/
                    Toast.makeText(getBaseContext(),getString(R.string.stream_started),Toast.LENGTH_SHORT).show(); //Notify user about starting stream
                }else if(toggle==1){
                    toggle=0;
                    fab.setImageResource(android.R.drawable.button_onoff_indicator_off);
                    Toast.makeText(getBaseContext(),getString(R.string.stream_stoped),Toast.LENGTH_SHORT).show(); //Notify user about stream being off
                }
                //snackbar
            }
        });

        // Add back <-- navigation to stream activity toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.streamToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
