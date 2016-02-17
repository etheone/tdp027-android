package com.act4heart.act4heart;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class RedClock {

    private long timeStamp;

    private JSONArray timerData;

    private SharedPreferences prefs;

    public RedClock(Context context) {
        // Required empty public constructor

        //Get saved timerdata
        prefs = context.getSharedPreferences(
                "timerData", 0);

        //Get current timestamp.
        timeStamp =  System.currentTimeMillis();

        //If we are continuing the clock in another fragment, we want the same
        //time to continue. Get the timestamp and give currentTime the correct
        //passed time.



        getTimerData();

        // prefs.edit().putLong("timeStamp", timeStamp).apply();


    }

    //Updates the time
    private void updateTimer(){
        /*currentTime += 1000;
        int currentSeconds = currentTime / 1000;
        int currentHours = currentSeconds/3600;

        //Gets the correct minutes
        int currentMinutes = currentSeconds/60 - currentHours * 60;
        currentSeconds = currentSeconds - (currentMinutes * 60) - (currentHours * 3600);*/

    }

    public void saveStartTime(){
        int timeStamp = (int)System.currentTimeMillis();

        try {
            //Puts the new Timestamp into the JSONArray and saves it to prefs
            timerData.put(new JSONObject().put("Start", timeStamp));
            String saveString = timerData.toString();
            prefs.edit().putString("timerData", saveString).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Function to save new variables to the timerData TODO save to shared?
    public void saveNewVariable(String key, String value){
        try {

            if(value == ""){

                //Get a timestamp for current time and puts it into timerdata.
                //In other words, this saves the time
                int timeStamp = (int)System.currentTimeMillis();
                ((JSONObject)timerData.get(timerData.length()-1)).put(key, timeStamp);
            } else {
                ((JSONObject)timerData.get(timerData.length()-1)).put(key, value);
            }
            String saveString = timerData.toString();
            prefs.edit().putString("timerData", saveString).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getTimerData();

    }

    //Fetches saved data.
    private void getTimerData(){

        //Gets the data from shared preferences
        String savedData = prefs.getString("timerData", null);

        JSONArray savedDataArray = null;

        if(savedData != null){
            //Converts the string data we just retrieved into an JSONArray
            try {
                savedDataArray = new JSONArray(savedData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

            //Makes sure we dont return a null JSONArray
            savedDataArray = new JSONArray();
        }



        timerData = savedDataArray;
    }
}
