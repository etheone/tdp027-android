package com.act4heart.act4heart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlueClockFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;
    private CountDownTimer clockTimer;

    private int countDown;

    public BlueClockFragment() {
        // Required empty public constructor
    }

    public static BlueClockFragment newInstance() {
        return new BlueClockFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.blue_clock_fragment, container, false);

        final TextView hours = (TextView) view.findViewById(R.id.hours);
        final TextView minutes = (TextView) view.findViewById(R.id.minutes);
        final TextView seconds = (TextView) view.findViewById(R.id.seconds);

        clockTimer = new CountDownTimer(countDown, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentSeconds = millisUntilFinished / 1000;
                long currentHours = currentSeconds/3600;

                //Gets the correct minutes
                long currentMinutes = currentSeconds/60 - currentHours * 60;
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

            @Override
            public void onFinish() {
                resetTimer();
            }
        };

        clockTimer.start();

        return view;
    }

    public void setTimer(int _countDown) {

        //Sets the time that we should count down from
        countDown = _countDown * 1000;
    }

    public void resetTimer(){
        clockTimer.cancel();
        clockTimer.start();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    //public interface OnFragmentInteractionListener {
        //void onFragmentInteraction(Uri uri);
    //}
}
