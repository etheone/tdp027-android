package com.act4heart.act4heart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

public class RedClockFragment extends Fragment {


    private int currentTime;
    private long timeStamp;
    private boolean continueClock = false;

    private  TextView hours;
    private  TextView minutes;
    private  TextView seconds;
    private  TextView seperatorHour;

    private View view;
    private SharedPreferences prefs;

    public RedClockFragment() {
        // Required empty public constructor
    }

    public static RedClockFragment newInstance() {
        return new RedClockFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.red_clock_fragment, container, false);

        //Get saved timerdata
        prefs = this.getActivity().getSharedPreferences(
                "timerPrefs", 0);

        //Get current timestamp.
        timeStamp =  System.currentTimeMillis();

        //If we are continuing the clock in another fragment, we want the same
        //time to continue. Get the timestamp and give currentTime the correct
        //passed time.
        if(continueClock){
            long time = prefs.getLong("timeStamp", new Date().getTime());
            currentTime = (int)(System.currentTimeMillis() - time);
        } else  {
            prefs.edit().putLong("timeStamp", timeStamp).apply();
        }

        //Textviews for the clock.
        hours = (TextView) view.findViewById(R.id.hours);
        minutes = (TextView) view.findViewById(R.id.minutes);
        seconds = (TextView) view.findViewById(R.id.seconds);

        //The seperator between hour and minutes
        seperatorHour = (TextView) view.findViewById(R.id.seperatorHour);

        timerHandler.post(timerRunnable);
        return view;
    }

    //The handler that handles the runnable
    private final Handler timerHandler = new Handler();

    //Timer runnable, updates every second
    private final Runnable timerRunnable = new Runnable() {
        public void run() {
            updateTimer();
            timerHandler.postDelayed(timerRunnable, 1000); // 1 second
        }
    };

    //Updates the time
    private void updateTimer(){
        currentTime += 1000;
        int currentSeconds = currentTime / 1000;
        int currentHours = currentSeconds/3600;

        //Gets the correct minutes
        int currentMinutes = currentSeconds/60 - currentHours * 60;
        currentSeconds = currentSeconds - (currentMinutes * 60) - (currentHours * 3600);

        //Makes sure that we keep format ex 00:00:00 or 09:20:10
        if(currentSeconds > 9){
            seconds.setText(String.valueOf(currentSeconds));
        } else {
            seconds.setText("0" + String.valueOf(currentSeconds));
        }

        if(currentMinutes > 9){
            minutes.setText(String.valueOf(currentMinutes));
        } else {
            minutes.setText("0" + String.valueOf(currentMinutes));
        }

        if(currentHours > 9){
            hours.setText(String.valueOf(currentHours));
        } else if( currentHours > 0 ){
            hours.setText("0" + String.valueOf(currentHours));

            //If the timer is below 1 hour, we set the field invisible once
            if(hours.getVisibility() == View.INVISIBLE) {
                hours.setVisibility(View.VISIBLE);
                seperatorHour.setVisibility(View.VISIBLE);
            }
        }
    }

    //Resets the timer and sets all fields correctly.
    public void resetTimer() {
        currentTime = 0;

        //Save the new timestamp
        timeStamp =  System.currentTimeMillis();
        prefs.edit().putLong("timeStamp", timeStamp).apply();


        hours.setVisibility(View.INVISIBLE);
        seperatorHour.setVisibility(View.INVISIBLE);
    }

    //Called from the fragment that implements the clock and want a new timestamp
    public void saveNewTimeStamp(){
        continueClock = false;
    }

    //Called from the fragment that implements the clock and want it to continue
    public void continueClock(){
        continueClock = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
