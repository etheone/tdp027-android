package com.act4heart.act4heart;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FirstTime extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_activity);

        TextView tv = (TextView) findViewById(R.id.ambulans_text);
        tv.setText(Html.fromHtml(getString(R.string.ambulans)));

        ImageView phoneIcon = (ImageView)findViewById(R.id.phone_icon);
        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyCall("0708565661");
            }
        });
    }

    private void emergencyCall(final String phoneNumber) {
        if (checkCallingOrSelfPermission("android.permission.CALL_PHONE") == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
            id = this.getTaskId();

            Runnable pushAppToForegound = new Runnable() {
                @Override
                public void run() {
                    ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                    activityManager.moveTaskToFront(id, 0);
                    ImageView iv = (ImageView) findViewById(R.id.phone_icon);
                    iv.setImageResource(R.drawable.phone_example2);
                }
            };
            Handler h = new Handler();
            h.postDelayed(pushAppToForegound, 1000);

        }
    }

}
