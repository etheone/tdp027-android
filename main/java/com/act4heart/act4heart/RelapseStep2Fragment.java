package com.act4heart.act4heart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.act4heart.act4heart.Symptoms.SOSCallActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link RelapseStep2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep2Fragment extends Fragment {

    private BlueClockFragment clock;
    private View v;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.relapse_step2_fragment, container, false);
        ((RelapseProcessActivity) getActivity()).canProceed = false;

        Button btn = (Button) v.findViewById(R.id.btn_to_emergency_call2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*If the timer has run out, we can proceed
                if(((RelapseProcessActivity)getActivity()).canProceed == true) {
                    goToStep3();
                }*/
                goToEmergencyCall();
            }
        });
        showFirstDialogMessage();

        // Inflate the layout for this fragment
        return v;
    }

    public void goToEmergencyCall(){
        Intent emergencyIntent = new Intent(getActivity(), SOSCallActivity.class);
        startActivity(emergencyIntent);
    }

    public void goToStep3() {
        RelapseStep3Fragment step3 = RelapseStep3Fragment.newInstance();
        clock.stopAlarm();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step3).commit();
    }

    private void activateBlueClock(){
        Button btn = (Button) v.findViewById(R.id.btn_to_emergency_call2);

        // Activate blue clock.
        clock = BlueClockFragment.newInstance();
        clock.setTimer(5);
        clock.linkButton(btn, 2);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step2, clock).commit();

        ((RelapseProcessActivity)this.getActivity()).redClock.saveNewVariable("Second","");
    }

    private void showFirstDialogMessage(){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage("Den blå klockan räknar ned tills du ska ta nästa dos");
        dlgAlert.setTitle("Ta en dos nitroglycerin");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activateBlueClock();
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(clock);
    }

}
