package com.act4heart.act4heart.Symptoms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.act4heart.act4heart.EmergencyCallHandler;
import com.act4heart.act4heart.MenuBarHandler;
import com.act4heart.act4heart.MyLocationListener;
import com.act4heart.act4heart.R;
import com.act4heart.act4heart.StartMenuActivity;

public class SOSCallActivity extends AppCompatActivity {

    ImageView phoneIcon;

    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos_call_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        phoneIcon = (ImageView)findViewById(R.id.phone_icon);


        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EmergencyCallHandler.isCallingEmergency) {
                    // End emergency call.
                    EmergencyCallHandler.endOngoingCall(getApplicationContext());
                    EmergencyCallHandler.isCallingEmergency = false;

                } else {
                    // Make emergency call
                    EmergencyCallHandler.isCallingEmergency= true;
                    makeCall();
                }
            }
        });

        /* Set up myLocationListener that deals with gps.
         * A reference to a TextView is sent so that the listener can
         * display the street of the current location
         */
        TextView positionText = (TextView) findViewById(R.id.positionText2);
        myLocationListener = new MyLocationListener(getApplicationContext(), positionText);


    }

    private void makeCall() {
        phoneIcon = (ImageView)findViewById(R.id.phone_icon);
        EmergencyCallHandler emergencyCallHandler = new EmergencyCallHandler(phoneIcon, this);
        emergencyCallHandler.emergencyCall(StartMenuActivity.EMERGENCY_PHONE_NUMBER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);
        MenuBarHandler.menuBarSetup(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnValue = MenuBarHandler.menuItemFunctionality(item, this);

        if(returnValue) return returnValue;
        return super.onOptionsItemSelected(item);
    }

    //If we are leaving the activity we must destroy the location listener.
    public void onStop(){
        super.onStop();
        myLocationListener.terminateService();
    }

    //If the service is not running, we must restart it here
    public void onResume(){
        super.onResume();
        if(myLocationListener.isTerminated()){
            myLocationListener.startService();
        }
    }

}
