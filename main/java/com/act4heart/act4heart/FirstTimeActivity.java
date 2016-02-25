package com.act4heart.act4heart;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.android.internal.telephony.*;

public class FirstTimeActivity extends AppCompatActivity {

    private int id;
    //private boolean isCallingEmergancy;
    ImageView phoneIcon;

    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);



        TextView tv = (TextView) findViewById(R.id.ambulans_text);
        tv.setText(Html.fromHtml(getString(R.string.ambulans)));



        phoneIcon = (ImageView)findViewById(R.id.phone_icon);


        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EmergencyCallHandler.isCallingEmergency) {
                    // End emergency call.
                    EmergencyCallHandler.endOngoingCall(getApplicationContext());
                    EmergencyCallHandler.isCallingEmergency = false;

                } else {
                    EmergencyCallHandler.isCallingEmergency= true;
                    makeCall();
                }
            }
        });

        TextView positionText = (TextView) findViewById(R.id.positionText2);
        myLocationListener = new MyLocationListener(getApplicationContext(), positionText);

        //getActionBar().setIcon(R.drawable.ic_volume_up);

    }

    private void makeCall() {
        phoneIcon = (ImageView)findViewById(R.id.phone_icon);
        EmergencyCallHandler emergencyCallHandler = new EmergencyCallHandler(phoneIcon, this);
        emergencyCallHandler.emergencyCall(StartMenu.EMERGENCY_PHONE_NUMBER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);

        if(StartMenu.soundOn) {
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_up);
        }
        else{
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_off);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_sound) {
            if(StartMenu.soundOn) {
                item.setIcon(R.drawable.ic_volume_off);
                StartMenu.soundOn = false;
            }
            else{
                item.setIcon(R.drawable.ic_volume_up);
                StartMenu.soundOn = true;
            }
            StartMenu.prefs.edit().putBoolean("soundOn", StartMenu.soundOn).commit();
            return true;
        }
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
