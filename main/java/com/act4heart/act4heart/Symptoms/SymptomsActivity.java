package com.act4heart.act4heart.Symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.act4heart.act4heart.EmergencyQuestions.RelapseQuestionActivity;
import com.act4heart.act4heart.EmergencySituationActivity;
import com.act4heart.act4heart.R;
import com.act4heart.act4heart.StartMenu;

import java.util.ArrayList;

public class SymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptoms_activity);

        Button continueButton = (Button) findViewById(R.id.btn_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueFromSymptoms();
            }
        });
        Button noSympton = (Button) findViewById(R.id.btn_no_sympton);
        noSympton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNoSymptoms();
            }
        });

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

    }

    private void startNoSymptoms() {
        Intent noSymptomsActivity = new Intent(this, NoSymptomsActivity.class);
        startActivity(noSymptomsActivity);
    }

    private void continueFromSymptoms() {
        ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox1));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox2));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox3));

        boolean danger = false;
        for (CheckBox checkBox : checkBoxes) {
            if(checkBox.isChecked()){
                danger = true;
            }
        }

        if(danger) {
            Intent relapseQuestion = new Intent(this, RelapseQuestionActivity.class);
            startActivity(relapseQuestion);
        }
        else{
            Intent noSymptomsActivity = new Intent(this, NoSymptomsActivity.class);
            startActivity(noSymptomsActivity);
        }
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
