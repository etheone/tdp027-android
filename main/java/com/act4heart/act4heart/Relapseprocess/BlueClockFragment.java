package com.act4heart.act4heart.Relapseprocess;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.act4heart.act4heart.R;
import com.act4heart.act4heart.StartMenuActivity;

public class BlueClockFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;
    private CountDownTimer clockTimer;

    private int countDown;
    private MediaPlayer ringtone;
    private Button btnLink = null;
    private String dialogMessage = "5 Minuter har gått!";

    // Sound variables
    private AudioManager mAudioManager;
    private int currentVolume;
    private boolean soundIsPlaying = false;

    private int currentStep = 1;

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
        ringtone = MediaPlayer.create(getContext(), R.raw.alarm);
        ringtone.setLooping(true);

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

                //TODO Only works once
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        +WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        +WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

                Intent openMainActivity= new Intent(getContext(), getActivity().getClass());
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(openMainActivity);

                //Sets the last tick in the clock fields.
                minutes.setText("00");
                seconds.setText("00");

                //Starts the alarm and open the alertbox when the blue clock timer runs out
                startAlarm();
                openAlert();

                //Unlocks the button to procceed to the next step.
                ((RelapseProcessActivity)getActivity()).canProceed = true;

                // Change the text of the now clickable button
                if(btnLink != null) {
                    btnLink.setAlpha(1.f);
                }
            }
        };

        clockTimer.start();

        return view;
    }

    public void setDialogText(String newMessage){
        dialogMessage = newMessage;
    }
    private void openAlert() {

        if (dialogMessage != null) {
            // Create a dialog alert that tells user some information
            // Disappear when the button is pressed.
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
            dlgAlert.setMessage(dialogMessage);
            dlgAlert.setTitle("Ta nästa dos nitroglycerin");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (currentStep) {
                                case 1:
                                    RelapseStep2Fragment step2 = RelapseStep2Fragment.newInstance();
                                    stopAlarm();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step2).commit();
                                    break;
                                case 2:
                                    RelapseStep3Fragment step3 = RelapseStep3Fragment.newInstance();
                                    stopAlarm();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step3).commit();
                                    break;
                                case 3:
                                    RelapseStep4Fragment step4 = RelapseStep4Fragment.newInstance();
                                    stopAlarm();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step4).commit();
                                    break;
                                case 4:
                                    stopAlarm();
                                    break;
                            }
                        }
                    });
            dlgAlert.create().show();
        }
    }

    //Starts the alarm sound
    private void startAlarm(){
        // AudioManager is used to increase volume to max if "soundOn" is true.
        if(StartMenuActivity.soundOn) {
            soundIsPlaying = true;

            mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

            //Makes sure we dont try to play a ringtone that does not exists
            if(ringtone != null) {
                ringtone.start();
            }
        }
    }

    //Stops the alarm sound
    public void stopAlarm(){
        if(soundIsPlaying) {
            soundIsPlaying = false;

            //audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);

            //Makes sure we dont try to stop a ringtone that does not exists
            if(ringtone != null) {
                ringtone.stop();
            }
        }

        //clockTimer.cancel();
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
        stopAlarm();

    }

    @Override
    public void onDetach(){
        super.onDetach();
        stopAlarm();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        stopAlarm();
        clockTimer.cancel();

    }

    //Used to link a button that is suppose to be activated when the clock timer runs out.
    //This also provides us with what fragment we are currently on.
    public void linkButton(Button btn, int step) {
        currentStep = step;
        btnLink = btn;
    }


    //public interface OnFragmentInteractionListener {
        //void onFragmentInteraction(Uri uri);
    //}
}
