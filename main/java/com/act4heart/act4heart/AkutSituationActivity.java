package com.act4heart.act4heart;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AkutSituationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akut_situation);

        Button button_start_relapse = (Button) findViewById(R.id.btn_aterfall);
        button_start_relapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRelapseActivity();

            }
        });
    }

    void startRelapseActivity() {
        Intent start_relapse_process = new Intent(this, RelapseProcessActivity.class);
        startActivity(start_relapse_process);
    }
}
