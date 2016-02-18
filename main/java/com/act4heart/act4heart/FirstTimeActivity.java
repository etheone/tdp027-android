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

            new CheckIfCallEnds().execute();
        }
    }


    // Private class of an AsyncCall that changes image view when the phone call ends.
    private class CheckIfCallEnds extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {
            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

            // Need to wait a little to get the correct phoneState.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Will end when phoneCall ends.
            while(tm.getCallState() == TelephonyManager.CALL_STATE_RINGING ||
                    tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        // Changes the imageViev for the phone when phone call is done.
        protected void onPostExecute(Void result) {
            ImageView iv = (ImageView) findViewById(R.id.phone_icon);
            iv.setImageResource(R.drawable.phone_example);
        }
    }


}
