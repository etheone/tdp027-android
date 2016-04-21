package com.act4heart.act4heart;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by joagy323 on 2016-04-21.
 */
public class MenuBarHandler {

    static public void menubarSetup(Menu menu){
        if(StartMenu.soundOn) {
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_up);
        }
        else{
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_off);
        }
    }

    static public boolean menuItemFunctionality(MenuItem item){
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
        return false;
    }
}
