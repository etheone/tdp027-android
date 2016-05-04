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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.act4heart.act4heart.EmergencyQuestions.RelapseQuestionActivity;
import com.act4heart.act4heart.InformationActivity;
import com.act4heart.act4heart.MenuBarHandler;
import com.act4heart.act4heart.R;

import java.util.ArrayList;

public class SymptomsActivity extends AppCompatActivity {

    private MyCustomCheckboxAdapter adapter;
    private int checkCount = 0;
    private boolean[] isChecked;
    //private boolean criticalSituation;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptoms_activity);

        continueButton = (Button) findViewById(R.id.btn_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueFromSymptoms();
            }
        });


        /*Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Symptom");
        }*/

        displayListView();
    }

    private void displayListView() {

        //Array list of symptoms
        final ArrayList<String> symptomsList = new ArrayList<String>();
        symptomsList.add("Ihållande bröstsmärta");
        symptomsList.add("Obehagskänsla i bröstet");
        symptomsList.add("Strålande i hals, käkar eller skuldror");
        symptomsList.add("Illamående");                                 // Critical
        symptomsList.add("Andnöd");
        symptomsList.add("Kallsvettning");                              // Critical
        symptomsList.add("Rädsla och ångest");
        symptomsList.add("Värk i ryggen");
        symptomsList.add("Hjärtklappning och yrsel");
        symptomsList.add("Influensaliknande besvär");

        isChecked = new boolean[symptomsList.size()];

        final ListView listView = (ListView)findViewById(R.id.symptomsListView);

        // Add adapter to listView
        adapter = new MyCustomCheckboxAdapter(this, android.R.layout.simple_selectable_list_item , symptomsList);
        listView.setAdapter(adapter);
    }

    /* A custom adapter that creates a listview of checkBoxes from an ArratList of strings*/
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.symptoms_checkbox_view, parent, false);

            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            if (isChecked[position])
                cb.setChecked(true);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox)v;
                    if(checkBox.isChecked()) {
                        checkCount++;
                        isChecked[position] = true;
                    } else{
                        checkCount--;
                        isChecked[position] = false;
                    }
                    System.out.println(checkCount);
                    if (checkCount > 0) {
                        continueButton.setText("Fortsätt");
                    } else {
                        continueButton.setText("Hoppa över");
                    }
                }
            });
            cb.setText(infoLines.get(position));

            return convertView;
        }

    } // End of MyCustomCheckboxAdapter Class.


    private void startInfoActivity() {
        Intent infoActivity = new Intent(this, InformationActivity.class);
        startActivity(infoActivity);
    }

    private void continueFromSymptoms() {
        if(checkCount > 0) {
            if(isChecked[3] || isChecked[5]) {
                Intent sosCall = new Intent(this, SOSCallActivity.class);
                startActivity(sosCall);
            } else {
                Intent relapseQuestion = new Intent(this, RelapseQuestionActivity.class);
                startActivity(relapseQuestion);
            }
        }
        else{
           startInfoActivity();
        }
    }


    /*
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
    }*/


}
