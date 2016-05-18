package com.act4heart.act4heart.Relapseprocess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.act4heart.act4heart.R;
import com.act4heart.act4heart.RedClockFragment;
import com.act4heart.act4heart.Symptoms.SOSCallActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link RelapseStep2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep2Fragment extends Fragment {

    private BlueClockFragment clock;
    private View view;
    private RedClockFragment redClock;

    public RelapseStep2Fragment() {
        // Required empty public constructor
    }
    public static RelapseStep2Fragment newInstance() {
        RelapseStep2Fragment fragment = new RelapseStep2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activate red clock
        redClock = RedClockFragment.newInstance();

        //Sets the clock to start
        redClock.saveNewTimeStamp();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.relapse_step2_fragment, container, false);
        ((RelapseProcessActivity) getActivity()).canProceed = false;

        //Take the user to the soscall activity-
        Button sosBtn = (Button) view.findViewById(R.id.btn_to_emergency_call2);
        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmergencyCall();
            }
        });
        showFirstDialogMessage();


        //inflate red clock fragment
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.redClockContainer, redClock).commit();

        // Inflate the layout for this fragment
        return view;
    }

    //Start sosactivity
    public void goToEmergencyCall(){
        Intent emergencyIntent = new Intent(getActivity(), SOSCallActivity.class);
        startActivity(emergencyIntent);
    }

    //Activates the blueclock with a timer
    private void activateBlueClock(){
        Button btn = (Button) view.findViewById(R.id.btn_to_emergency_call2);

        // Activate blue clock and select which button you want to set to active(
        //provide this with what step you want to move to)
        clock = BlueClockFragment.newInstance();
        clock.setTimer(30);
        clock.linkButton(btn, 2);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step2, clock).commit();


        //Saves the first timestamp for this emergency
        ((RelapseProcessActivity)this.getActivity()).timeStampHandler.saveStartTime();
    }

    private void showFirstDialogMessage(){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage("Den blå klockan räknar ned tills du ska ta nästa dos");
        dlgAlert.setTitle("Ta en dos nitroglycerin");
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activateBlueClock();
                    }
                });
        dlgAlert.create().show();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(clock);
    }

}
