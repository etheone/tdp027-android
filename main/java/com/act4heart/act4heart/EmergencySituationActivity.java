package com.act4heart.act4heart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.act4heart.act4heart.Relapseprocess.RelapseProcessActivity;
import com.act4heart.act4heart.Symptoms.SOSCallActivity;
import com.act4heart.act4heart.database.DBHandler;

public class EmergencySituationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_situation);

        // Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Akutsituation");
        }

        // Add onClickListeners to buttons.
        Button buttonStartRelapse = (Button) findViewById(R.id.btn_nitro);
        buttonStartRelapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRelapseActivity();

            }
        });
        Button buttonEmergencyCall = (Button) findViewById(R.id.btn_no_nitro);
        buttonEmergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSOSCallActivity();
            }
        });
    }

    private void startSOSCallActivity(){
        DBHandler.sendInfoToDB("SOSCallActivity", this);
        Intent intent = new Intent(this, SOSCallActivity.class);
        startActivity(intent);
    }

    private void startRelapseActivity() {
        DBHandler.sendInfoToDB("RelapseProcessActivity", this);
        Intent start_relapse_process = new Intent(this, RelapseProcessActivity.class);
        startActivity(start_relapse_process);
    }

    // OPTIONS
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

}
