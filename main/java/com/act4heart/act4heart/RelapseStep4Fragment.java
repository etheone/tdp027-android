package com.act4heart.act4heart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link RelapseStep4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep4Fragment extends Fragment {

    private BlueClockFragment clock;

    public RelapseStep4Fragment() {
        // Required empty public constructor
    }
    public static RelapseStep4Fragment newInstance() {
        RelapseStep4Fragment fragment = new RelapseStep4Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.relapse_step4_fragment, container, false);
        ((RelapseProcessActivity) getActivity()).canProceed = false;

        Button btn = (Button) view.findViewById(R.id.stillHurting);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If the timer has run out, we can proceed
                if(((RelapseProcessActivity)getActivity()).canProceed == true) {
                    goToLastStep();
                }
            }
        });

        Button btnNotHurting = (Button) view.findViewById(R.id.notHurtingButton);
        btnNotHurting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If the timer has run out, we can proceed
                if(((RelapseProcessActivity)getActivity()).canProceed == true) {
                    goToEmergencyGoneStep();
                }
            }
        });
        // Activate blue clock.
        clock = BlueClockFragment.newInstance();
        clock.setTimer(5);
        clock.setDialogText("Välj något av alternativen!");
        clock.linkButton(null, 4);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step4, clock).commit();

        ((RelapseProcessActivity)this.getActivity()).redClock.saveNewVariable("Fourth", "");

        // Inflate the layout for this fragment
        return view;
    }

    public void goToLastStep() {
        RelapseLastPageFragment lastStep = RelapseLastPageFragment.newInstance();
        clock.stopAlarm();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, lastStep).commit();
    }

    public void goToEmergencyGoneStep() {
        RelapseEmergencyGoneFragment emergencyGoneStep = RelapseEmergencyGoneFragment.newInstance();
        clock.stopAlarm();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, emergencyGoneStep).commit();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(clock);
    }

}
