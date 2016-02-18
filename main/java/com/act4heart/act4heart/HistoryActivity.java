package com.act4heart.act4heart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        JSONObject test = new JSONObject();
        String a = "";
        try {
            a = (String) test.get("hej");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("test", a);
    }

}
