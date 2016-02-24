package com.act4heart.act4heart;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.ImageView;

/**
 * Created by joagy323 on 2016-02-18.
 */
public class EmergencyCallHandler {

    private ImageView imageViewToChange;
    private int callerID;
    private AppCompatActivity callerActivity;

    public EmergencyCallHandler(ImageView view, AppCompatActivity caller){
        this.callerActivity = caller;
        imageViewToChange = view;
    }

    // Makes an emergencyCall to "phoneNumber" by starting a CallActivity and a Runnable that
    // pushes the original app to the foreground after 1 second
    public void emergencyCall(final String phoneNumber) {
        if (callerActivity.checkCallingOrSelfPermission("android.permission.CALL_PHONE") == PackageManager.PERMISSION_GRANTED) {
            callerActivity.startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
            callerID = callerActivity.getTaskId();

            Runnable pushAppToForegound = new Runnable() {
                @Override
                public void run() {
                    ActivityManager activityManager = (ActivityManager) callerActivity.getSystemService(Context.ACTIVITY_SERVICE);
                    activityManager.moveTaskToFront(callerID, 0);
                    imageViewToChange.setImageResource(R.drawable.phone_example2);
                }
            };
            Handler h = new Handler();
            h.postDelayed(pushAppToForegound, 1000);

            // start a thread that keeps track of phone state. changes phone icon when call ends.
            new CheckIfCallEnds().execute();
        }
    }


    // Private class of an AsyncTask that changes image view when the phone call ends.
    private class CheckIfCallEnds extends AsyncTask<Void, Void, Void> {


        protected Void doInBackground(Void... params) {
            TelephonyManager tm = (TelephonyManager) callerActivity.getSystemService(Context.TELEPHONY_SERVICE);

            // Need to wait a little to get the correct phoneState.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Will end when phoneCall ends.
            while (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING ||
                    tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
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
            imageViewToChange.setImageResource(R.drawable.callbutton);
        }
    }

}
