package com.act4heart.act4heart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AppInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_info_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);

        if(StartMenuActivity.soundOn) {
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


        if (id == R.id.action_sound) {
            if(StartMenuActivity.soundOn) {
                item.setIcon(R.drawable.ic_volume_off);
                StartMenuActivity.soundOn = false;
            }
            else{
                item.setIcon(R.drawable.ic_volume_up);
                StartMenuActivity.soundOn = true;
            }
            StartMenuActivity.prefs.edit().putBoolean("soundOn", StartMenuActivity.soundOn).commit();
            return true;
        }

        if (id == R.id.home_button) {
            Intent homeAcitivity = new Intent(this, StartMenuActivity.class);
            homeAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeAcitivity);
        }

        return super.onOptionsItemSelected(item);
    }

}

