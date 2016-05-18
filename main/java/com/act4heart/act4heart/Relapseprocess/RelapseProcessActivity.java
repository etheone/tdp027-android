package com.act4heart.act4heart.Relapseprocess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.act4heart.act4heart.MenuBarHandler;
import com.act4heart.act4heart.R;
import com.act4heart.act4heart.Symptoms.SymptomsActivity;
import com.act4heart.act4heart.TimeStampHandler;
import com.act4heart.act4heart.unused.RelapseStep1Fragment;
import com.act4heart.act4heart.StartMenuActivity;

public class RelapseProcessActivity extends AppCompatActivity {


    //If the timer has run out
    public Boolean canProceed = false;

    //Used to save timestamps for the historyactivity
    public TimeStampHandler timeStampHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relapse_process);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        //Set the toolbar name
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Återfall");
        }

        //Instatiate timeStampHandler
        timeStampHandler = new TimeStampHandler(this);

        switchFragment(2);

    }

    //Takes an argument that is used to determine the next step in the relapseprocess
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
        MenuBarHandler.menuBarSetup(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnValue = MenuBarHandler.menuItemFunctionality(item, this);

        if(returnValue) return returnValue;
        return super.onOptionsItemSelected(item);
    }

    //Override the standard back button function
    @Override
    public void onBackPressed(){

        //We should open an alert if the user tries to go back inside the relapseprocess
        openAlert();
    }

    public void openAlert() {

        // Create a dialog alert that tells user that you are ending the emergency process
        // Choose either Ja to end the process or no to continue
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Vill du verkligen avbryta och återgå till huvudmenyn?");
        dlgAlert.setTitle("OBS!");
        dlgAlert.setPositiveButton("Ja",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //Change to startMenu Activity
                        //Intent startMenu = new Intent(getApplicationContext(), StartMenuActivity.class);
                        //startActivity(startMenu);

                        endRelapseProcess();
                        

                    }
                });
        dlgAlert.setNegativeButton("Nej", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void endRelapseProcess() {
        //Returns the user home. Removes the activity stack so that the backbutton does not
        // take us back to the fragments.
        Intent homeAcitivity = new Intent(this, SymptomsActivity.class);

        homeAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeAcitivity);
    }


}
