package com.act4heart.act4heart.database;

import android.content.Context;
import android.provider.Settings.Secure;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class DBHandler {

    private static String phoneId;

    public static void setPhoneId(String id) {
        phoneId = id;
    }

    // for debugging
    public static String getPhoneId() {
        return phoneId;
    }

    public  static void sendInfoToDB(String activity, Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url ="http://act4heart-act4heart.rhcloud.com/sendData?user=" + phoneId + "&path=" + activity + "&unixtime=" + System.currentTimeMillis()/1000L;
        // http://act4heartweb-act4heartweb.rhcloud.com/
        String url ="http://act4heartweb-act4heartweb.rhcloud.com/sendData?user=" + phoneId + "&path=" + activity + "&unixtime=" + System.currentTimeMillis()/1000L;
        //System.out.println(System.currentTimeMillis());
        //System.out.println(System.currentTimeMillis()/1000L);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        System.out.println("#################################################");
                        System.out.println("############# We got a response from GET request.");
                        System.out.println("#################################################");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("There was an error with the GET request. $$$$$$$$$$$$$");
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
