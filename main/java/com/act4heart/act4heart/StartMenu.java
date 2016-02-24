package com.act4heart.act4heart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartMenu extends AppCompatActivity{

    public static boolean soundOn = true;
    public static SharedPreferences prefs;

    private Menu thisMenu = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu);

        //Get previous value for the sound
        prefs = getSharedPreferences(
                "soundOn", 0);
        soundOn = prefs.getBoolean("soundOn", true);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        Button btnEmergency = (Button) findViewById(R.id.btn_akut);
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEmergencyActivity();
            }
        });

        Button btnStartGame = (Button) findViewById(R.id.btn_game);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity();
            }
        });
    }

    void startEmergencyActivity() {
        Intent emergency = new Intent(this, EmergencySituationActivity.class);
        startActivity(emergency);
    }

    void startGameActivity() {
        Intent game = new Intent(this, HeartGameActivity.class);
        startActivity(game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);

        thisMenu = menu;
        fixSoundIcon();

        return true;
    }

    private void fixSoundIcon(){
        if(soundOn) {
            ((MenuItem)thisMenu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_up);
        }
        else{
            ((MenuItem)thisMenu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_off);
        }
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
            if(soundOn) {
                item.setIcon(R.drawable.ic_volume_off);
                soundOn = false;
            }
            else{
                item.setIcon(R.drawable.ic_volume_up);
                soundOn = true;
            }
            prefs.edit().putBoolean("soundOn", soundOn).commit();
            return true;
        }
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
