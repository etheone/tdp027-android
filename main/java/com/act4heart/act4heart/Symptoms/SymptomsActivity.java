package com.act4heart.act4heart.Symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.act4heart.act4heart.EmergencySituationActivity;
import com.act4heart.act4heart.R;

public class SymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptoms_activity);

        Button worstButton = (Button) findViewById(R.id.worst_symptoms_btn);
        worstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmergencySituationActivity();
            }
        });

        Button badButton = (Button) findViewById(R.id.bad_symptom_btn);
        badButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmergencySituationActivity();
            }
        });

        Button noSymptomButton = (Button) findViewById(R.id.no_symptom_btn);
        noSymptomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNoSymptomsActivity();
            }
        });

    }

    private void goToNoSymptomsActivity() {
        Intent emergency = new Intent(this, NoSymptomsActivity.class);
        startActivity(emergency);
    }

    private void goToEmergencySituationActivity() {
        Intent emergency = new Intent(this, EmergencySituationActivity.class);
        startActivity(emergency);
    }

}
