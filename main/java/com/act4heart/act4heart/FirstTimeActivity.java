package com.act4heart.act4heart;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FirstTimeActivity extends AppCompatActivity {

    private int id;
    ImageView phoneIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_activity);

        TextView tv = (TextView) findViewById(R.id.ambulans_text);
        tv.setText(Html.fromHtml(getString(R.string.ambulans)));



        phoneIcon = (ImageView)findViewById(R.id.phone_icon);


        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        TextView positionText = (TextView) findViewById(R.id.positionText2);
        MyLocationListener myLocationListener = new MyLocationListener(getApplicationContext(), positionText);


    }

    private void makeCall() {
        phoneIcon = (ImageView)findViewById(R.id.phone_icon);
        EmergencyCallHandler emergencyCallHandler = new EmergencyCallHandler(phoneIcon, this);
        emergencyCallHandler.emergencyCall("0708565661");
    }

}
