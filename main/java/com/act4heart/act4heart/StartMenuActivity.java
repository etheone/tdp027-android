package com.act4heart.act4heart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.act4heart.act4heart.HeartGame.HeartGameActivity;
import com.act4heart.act4heart.database.DBHandler;
import com.act4heart.act4heart.Symptoms.SymptomsActivity;

public class StartMenuActivity extends AppCompatActivity{

    public static boolean soundOn = true;
    public static SharedPreferences prefs;
    public final static String EMERGENCY_PHONE_NUMBER = "0706190790";

    private Menu thisMenu = null;

    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu);

        //Get stored value for the sound
        prefs = getSharedPreferences("soundOn", 0);
        soundOn = prefs.getBoolean("soundOn", true);

        /*Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("");
        }

        Button btnEmergency = (Button) findViewById(R.id.btn_akut);
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEmergencyActivity();
            }
        });*/


        // set the phone id in the static class DBHandler
        DBHandler.setPhoneId(Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));


       /* Button btnInfo = (Button) findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInfoActivity();
            }
        });*/






        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startEmergencyActivity();

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    void startEmergencyActivity() {
        DBHandler.sendInfoToDB("SymptomActivity", this);
        Intent emergency = new Intent(this, SymptomsActivity.class);
        startActivity(emergency);
    }

    private void startInfoActivity(){
        DBHandler.sendInfoToDB("InformationActivity", this);
        Intent info = new Intent(this, InformationActivity.class);
        startActivity(info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);
        MenuBarHandler.menuBarSetup(menu);

        return true;
    }

    private void fixSoundIcon(){
        if(soundOn) {
            (thisMenu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_up);
        }
        else{
            (thisMenu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_off);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnValue = MenuBarHandler.menuItemFunctionality(item, this);

        if(returnValue) return returnValue;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (thisMenu != null) {
            fixSoundIcon();
        }
    }

}
