package com.act4heart.act4heart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.act4heart.act4heart.Relapseprocess.RelapseProcessActivity;

/**
 * Created by joagy323 on 2016-04-21.
 */
public class MenuBarHandler {

    static public void menuBarSetup(Menu menu){
        if(StartMenuActivity.soundOn) {
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_up);
        }
        else{
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_off);
        }
    }

    static public boolean menuItemFunctionality(MenuItem item, final Context context) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_sound) {

            if (StartMenuActivity.soundOn) {
                item.setIcon(R.drawable.ic_volume_off);
                StartMenuActivity.soundOn = false;
            } else {

                if (StartMenuActivity.soundOn) {
                    item.setIcon(R.drawable.ic_volume_off);
                    StartMenuActivity.soundOn = false;
                } else {

                    item.setIcon(R.drawable.ic_volume_up);
                    StartMenuActivity.soundOn = true;
                }
                StartMenuActivity.prefs.edit().putBoolean("soundOn", StartMenuActivity.soundOn).commit();
                return true;
            }
        }
            if (id == R.id.home_button) {

                Intent homeAcitivity = new Intent(context, StartMenuActivity.class);
                homeAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(homeAcitivity);
                // Create a dialog alert that tells user that you are ending the emergency process
                // Choose either Ja to end the process or no to continue
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                dlgAlert.setMessage("Vilken du verkligen avbryta och återgå till huvudmenyn?");
                dlgAlert.setTitle("OBS!");
                dlgAlert.setPositiveButton("Ja",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                endRelapseProcess(context);


                            }
                        });
                dlgAlert.setNegativeButton("Nej", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }
            return false;
        }

    private static void endRelapseProcess(Context context) {

        //Returns the user home. Removes the activity stack so that the backbutton does not
        // take us back to the fragments.
        Intent homeAcitivity = new Intent(context, StartMenuActivity.class);
        homeAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(homeAcitivity);
    }
}
