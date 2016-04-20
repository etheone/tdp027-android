package com.act4heart.act4heart.Symptoms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.act4heart.act4heart.EmergencyQuestions.RelapseQuestionActivity;
import com.act4heart.act4heart.EmergencySituationActivity;
import com.act4heart.act4heart.R;
import com.act4heart.act4heart.StartMenu;

import java.util.ArrayList;

public class SymptomsActivity extends AppCompatActivity {

    private MyCustomCheckboxAdapter adapter;
    private int checkCount = 0;

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


        final ListView listView = (ListView)findViewById(R.id.symptomsListView);


        adapter = new MyCustomCheckboxAdapter(this, android.R.layout.simple_selectable_list_item , symptomsList);
        listView.setAdapter(adapter);
    }

    private class MyCustomCheckboxAdapter extends ArrayAdapter<String>{
        private ArrayList<String> infoLines;
        private Context context;

        public MyCustomCheckboxAdapter(Context context, int textViewResourceId,
                               ArrayList<String> infoLines) {
            super(context, textViewResourceId, infoLines);
            this.context = context;
            this.infoLines = new ArrayList<String>();
            this.infoLines.addAll(infoLines);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.symptoms_checkbox_view, parent, false);

            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox)v;
                    if(checkBox.isChecked()) {
                        checkCount++;
                    } else{
                        checkCount--;
                    }
                    System.out.println(checkCount);
                }
            });
            cb.setText(infoLines.get(position));


            return convertView;
        }

    }


    private void startNoSymptoms() {
        Intent noSymptomsActivity = new Intent(this, NoSymptomsActivity.class);
        startActivity(noSymptomsActivity);
    }

    private void continueFromSymptoms() {

        if(checkCount > 0) {
            Intent relapseQuestion = new Intent(this, RelapseQuestionActivity.class);
            startActivity(relapseQuestion);
        }
        else{
           startNoSymptoms();
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

}
