package com.act4heart.act4heart;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class RelapseProcessActivity extends AppCompatActivity {


    //If the timer has run out
    public Boolean canProceed = false;
    public RedClock redClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relapse_process);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        redClock = new RedClock(this);
        //RelapseStep1Fragment step1 = RelapseStep1Fragment.newInstance();
        // getSupportFragmentManager().beginTransaction()
       // .replace(R.id.fragment_container, gMapFragment).commit();

        //getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step1).commit();
        switchFragment(1);
    }

    public void switchFragment(int nextFragment){
        Fragment fragment = null;
        if(nextFragment == 1){
            fragment = RelapseStep1Fragment.newInstance();
        } else if (nextFragment == 2) {
            fragment= RelapseStep2Fragment.newInstance();
        } else if (nextFragment == 3) {
            fragment= RelapseStep2Fragment.newInstance();
        } else if (nextFragment == 4) {
            fragment= RelapseStep2Fragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, fragment).commit();

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

}
