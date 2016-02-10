package com.act4heart.act4heart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlueClockFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;
    private CountDownTimer clockTimer;

    private int countDown;
    private Ringtone ringtone;

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

        //Initializes the ringtone that is to be played when the timer runs out
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getContext(), notification);

        //The seperator between hour and minutes
        final TextView seperatorHour = (TextView) view.findViewById(R.id.seperatorHour);

        clockTimer = new CountDownTimer(countDown, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentSeconds = (long) Math.floor(millisUntilFinished / 1000);
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
                } else if( currentHours > 0 ){
                    hours.setText("0" + String.valueOf(currentHours));

                    //If the timer is below 1 hour, we set the field invisible once
                    if(hours.getVisibility() == View.INVISIBLE) {
                        hours.setVisibility(View.VISIBLE);
                        seperatorHour.setVisibility(View.VISIBLE);
                    }
                } else if (hours.getVisibility() == View.VISIBLE) {
                    hours.setVisibility(View.INVISIBLE);
                    seperatorHour.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFinish() {

                //Sets the last tick in the clock fields.
                minutes.setText("00");
                seconds.setText("00");

                //Starts the alarm and open the alertbox when the blue clock timer runs out
                startAlarm();
                openAlert();

                //Unlocks the button to procceed to the next step.
                ((RelapseProcessActivity)getActivity()).canProceed = true;
            }
        };

        clockTimer.start();

        return view;
    }

    private void openAlert() {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage("You are allowed to move on ... RIP <3");
        dlgAlert.setTitle("Time's up!");
        dlgAlert.setPositiveButton("Ok...",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });        dlgAlert.setCancelable(true);
        dlgAlert.create().show();    }

    //Starts the alarm sound
    private void startAlarm(){
        ringtone.play();
    }

    //Stops the alarm sound
    public void stopAlarm(){
        ringtone.stop();
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
    public void onStop(){
        super.onStop();

        ringtone.stop();
    }

    @Override
    public void onDetach(){
        super.onDetach();

        clockTimer.cancel();
        ringtone.stop();
    }


    //public interface OnFragmentInteractionListener {
        //void onFragmentInteraction(Uri uri);
    //}
}
