package com.act4heart.act4heart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        Button btnStartGuide = (Button) findViewById(R.id.btn_guide);
        btnStartGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGuideActivity();
            }
        });

        Button btnHeartFailureInfo = (Button) findViewById(R.id.btn_heart_failure_info);
        btnHeartFailureInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartFailureInfoActivity();
            }
        });

        Button btnAppInfo = (Button) findViewById(R.id.btn_app_info);
        btnAppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppInfoActivity();
            }
        });
    }


    private void AppInfoActivity(){
        Intent appInfoIntent = new Intent(this, AppInfoActivity.class);
        startActivity(appInfoIntent);
    }

    private void heartFailureInfoActivity(){
        Intent startHeartFailureInfo = new Intent(this, HeartFailureInfoActivity.class);
        startActivity(startHeartFailureInfo);
    }

    private void startGuideActivity(){
        Intent startGuide = new Intent(this, GuideActivity.class);
        startActivity(startGuide);
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
