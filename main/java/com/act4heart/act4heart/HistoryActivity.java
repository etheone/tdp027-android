package com.act4heart.act4heart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class HistoryActivity extends AppCompatActivity {

    Bundle state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        state = savedInstanceState;

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Historik");
        }

        //adds fragment to view
        HistoryListFragment historyListFragment = HistoryListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.historyContainer, historyListFragment, "historyFragment").addToBackStack("historyFragment").commit();
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

        if (id == R.id.home_button) {
            Intent homeAcitivity = new Intent(this, StartMenu.class);
            homeAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeAcitivity);
        }

        return super.onOptionsItemSelected(item);
    }

    //overrides the back press button and finds the fragments so new one can be removed and old one replaces it
    @Override
    public void onBackPressed(){

        //fetches fragments by tag
        HistoryEntryFragment historyEntryFragment = (HistoryEntryFragment)getSupportFragmentManager().findFragmentByTag("historyEntryFragment");
        HistoryListFragment historyListFragment = (HistoryListFragment) getSupportFragmentManager().findFragmentByTag("historyFragment");
        if(historyEntryFragment != null) {

            //removes new fragment from backstack, replaces the container with the old fragment and removes the new fragment
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, historyListFragment, "historyFragment").addToBackStack("historyFragment")
                    .remove(historyEntryFragment)
                    .commit();


        } else {
            //ends activity
            finish();
        }
    }


}
