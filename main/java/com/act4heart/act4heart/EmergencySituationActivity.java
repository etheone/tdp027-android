package com.act4heart.act4heart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EmergencySituationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_situation);

        Button buttonStartRelapse = (Button) findViewById(R.id.btn_start_relapse);
        buttonStartRelapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRelapseActivity();

            }
        });
        Button buttonEmergencyCall = (Button) findViewById(R.id.btn_emergency_call);
        buttonEmergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmergencyCall("0764096617");
            }
        });
    }

    private void startRelapseActivity() {
        Intent start_relapse_process = new Intent(this, RelapseProcessActivity.class);
        startActivity(start_relapse_process);
    }

    private void EmergencyCall(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
