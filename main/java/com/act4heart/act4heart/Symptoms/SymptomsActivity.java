package com.act4heart.act4heart.Symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

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
        /*Button noSympton = (Button) findViewById(R.id.btn_no_sympton);
        noSympton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNoSymptoms();
            }
        });*/

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Symptom");
        }

        displayListView();

    }

    private void displayListView() {

        //Array list of symptoms
        final ArrayList<String> symptomsList = new ArrayList<String>();
        symptomsList.add("Stark och ihållande bröstsmärta");
        symptomsList.add("Obehagskänsla i bröstet");
        symptomsList.add("Strålande i halsen");
        symptomsList.add("Käkarna eller skuldrorna värker");
        symptomsList.add("Illamående");
        symptomsList.add("Andnöd");
        symptomsList.add("Kallsvettningar");
        symptomsList.add("Rädsla och ångest");
        symptomsList.add("Värk i ryggen");
        symptomsList.add("Hjärtklappning och yrsel");
        symptomsList.add("Jag känner inga av dessa symtpom");


        final ListView listView = (ListView)findViewById(R.id.symptomsListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(symptomsList.size() - 1 == position) {
                    startNoSymptoms();
                }else {
                    continueFromSymptoms();
                }
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item , symptomsList);
        listView.setAdapter(adapter);
    }


    private void startNoSymptoms() {
        Intent noSymptomsActivity = new Intent(this, NoSymptomsActivity.class);
        startActivity(noSymptomsActivity);
    }

    private void continueFromSymptoms() {
      /*  ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox1));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox2));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox3));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox4));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox5));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox6));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox7));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox8));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox9));

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
        }*/
        Intent relapseQuestion = new Intent(this, RelapseQuestionActivity.class);
        startActivity(relapseQuestion);
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
