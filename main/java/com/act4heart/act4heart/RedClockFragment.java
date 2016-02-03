package com.act4heart.act4heart;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RedClockFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private int currentTime = 100000;
    private  TextView hours;
    private  TextView minutes;
    private  TextView seconds;

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
        View view = inflater.inflate(R.layout.red_clock_fragment, container, false);

        hours = (TextView) view.findViewById(R.id.hours);
        minutes = (TextView) view.findViewById(R.id.minutes);
        seconds = (TextView) view.findViewById(R.id.seconds);

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
        } else {
            hours.setText("0" + String.valueOf(currentHours));
        }
    }

    public void resetTimer(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        //void onFragmentInteraction(Uri uri);
    }
}
