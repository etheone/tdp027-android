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
import com.act4heart.act4heart.MenuBarHandler;
import com.act4heart.act4heart.R;

import java.util.ArrayList;

public class SymptomsActivity extends AppCompatActivity {

    private MyCustomCheckboxAdapter adapter;
    private int checkCount = 0;
    private boolean[] isChecked;

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

        isChecked = new boolean[symptomsList.size()];


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
        MenuBarHandler.menubarSetup(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnValue = MenuBarHandler.menuItemFunctionality(item);

        if(returnValue) return returnValue;
        return super.onOptionsItemSelected(item);
    }


}
