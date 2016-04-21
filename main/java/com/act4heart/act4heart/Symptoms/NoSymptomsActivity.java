package com.act4heart.act4heart.Symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.act4heart.act4heart.EmergencySituationActivity;
import com.act4heart.act4heart.R;
import com.act4heart.act4heart.StartMenuActivity;

public class NoSymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_symptoms_activity);

        Button goToCallButton = (Button) findViewById(R.id.go_to_call_btn);
        goToCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmergencySituation();
            }
        });

        Button goBackButton = (Button) findViewById(R.id.go_back_btn);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStartMenu();
            }
        });

    }

    private void goToStartMenu() {
        Intent emergency = new Intent(this, StartMenuActivity.class);
        startActivity(emergency);
    }

    private void goToEmergencySituation() {
        Intent emergency = new Intent(this, EmergencySituationActivity.class);
        startActivity(emergency);
    }



}
