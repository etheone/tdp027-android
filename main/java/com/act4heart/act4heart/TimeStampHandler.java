package com.act4heart.act4heart;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimeStampHandler {

    private JSONArray timerData;

    private SharedPreferences prefs;

    public TimeStampHandler(Context context) {
        // Required empty public constructor

        //Get saved timerdata
        prefs = context.getSharedPreferences(
                "timerData", 0);

        //Updates the private variable timerData with the saved data
        retrieveTimerData();
    }

    public void saveStartTime(){
        int timeStamp = (int)System.currentTimeMillis();

        try {
            //Puts the new Timestamp into the JSONArray and saves it to prefs
            timerData.put(new JSONObject().put("Start",  new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss").format(Calendar.getInstance().getTime())));
            ((JSONObject)timerData.get(timerData.length()-1)).put("Second", "");
            ((JSONObject)timerData.get(timerData.length()-1)).put("Third", "");
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
                ((JSONObject)timerData.get(timerData.length()-1)).put(key, new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss").format(Calendar.getInstance().getTime()));
            } else {
                ((JSONObject)timerData.get(timerData.length()-1)).put(key, value);
            }

            //Saves the timerData to the device
            String saveString = timerData.toString();
            prefs.edit().putString("timerData", saveString).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Returns hours and minutes of the saved data belonging to the key
    public String getSavedHHmmByKey(String key){

        String returnString = "";
        try {

            //Gets the data value for this key and chooses only hours and minutes to return
            String data = (String)((JSONObject)timerData.get(timerData.length()-1)).get(key);
            String[] temp = data.split(",");
            returnString = temp[3] + ":" + temp[4];

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    //Updates the private variable timerData with the saved data
    private void retrieveTimerData(){

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

            //Makes sure we don't save a null JSONArray
            savedDataArray = new JSONArray();
        }

        timerData = savedDataArray;
    }

}
