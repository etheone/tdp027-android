package com.act4heart.act4heart.EmergencyQuestions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.act4heart.act4heart.EmergencySituationActivity;
import com.act4heart.act4heart.R;
import com.act4heart.act4heart.Symptoms.SOSCallActivity;
import com.act4heart.act4heart.database.DBHandler;

public class RelapseQuestionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relapse_question_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        Button buttonRelapse = (Button) findViewById(R.id.btn_relapse);
        buttonRelapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNitroQuestionActivity();

            }
        });
        Button buttonNoRelapse = (Button) findViewById(R.id.btn_no_relapse);
        buttonNoRelapse.setOnClickListener(new View.OnClickListener() {
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

    private void startNitroQuestionActivity() {
        DBHandler.sendInfoToDB("EmergencySituationActivity", this);
        Intent start_relapse_process = new Intent(this, EmergencySituationActivity.class);
        startActivity(start_relapse_process);
    }
}
