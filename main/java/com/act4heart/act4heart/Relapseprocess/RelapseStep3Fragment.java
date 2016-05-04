package com.act4heart.act4heart.Relapseprocess;

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
 * Use the {@link RelapseStep3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep3Fragment extends Fragment {

    private BlueClockFragment clock;

    public RelapseStep3Fragment() {
        // Required empty public constructor
    }

    public static RelapseStep3Fragment newInstance() {
        RelapseStep3Fragment fragment = new RelapseStep3Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.relapse_step3_fragment, container, false);
        ((RelapseProcessActivity) getActivity()).canProceed = false;

        //Take the user to the soscall activity
        Button sosBtn = (Button) view.findViewById(R.id.btn_to_emergency_call3);
        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEmergencyCall();
            }
        });

        // Activate blue clock and select which button you want to set to active(
        //provide this with what step you want to move to)
        clock = BlueClockFragment.newInstance();
        clock.setTimer(5);
        clock.linkButton(sosBtn,3);

        //Puts a clock fragment for this relapsefragment into the clock_container.
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step3, clock).commit();

        ((RelapseProcessActivity)this.getActivity()).timeStampHandler.saveNewVariable("Second", "");

        //Activate red clock
        RedClockFragment redClock = RedClockFragment.newInstance();

        //Sets the clock to continue
        redClock.continueClock();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.redClockContainer, redClock).commit();

        // Inflate the layout for this fragment
        return view;
    }

    public void goToEmergencyCall(){
        Intent emergencyIntent = new Intent(getActivity(), SOSCallActivity.class);
        startActivity(emergencyIntent);
    }

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(clock);
    }

}
