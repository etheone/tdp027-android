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
 * Use the {@link RelapseStep2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep2Fragment extends Fragment {

    private BlueClockFragment clock;

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
        View v = inflater.inflate(R.layout.relapse_step2_fragment, container, false);
        Button btn = (Button) v.findViewById(R.id.btn_to_step3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the timer has run out, we can proceed
                if(((RelapseProcessActivity)getActivity()).canProceed == true) {
                    ((RelapseProcessActivity) getActivity()).canProceed = false;
                    goToStep3();
                }
            }
        });

        // Activate blue clock.
        clock = BlueClockFragment.newInstance();
        clock.setTimer(5);
        clock.linkButton(btn);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step2, clock).commit();

        ((RelapseProcessActivity)this.getActivity()).redClock.saveNewVariable("Second","");

        // Inflate the layout for this fragment
        return v;
    }

    public void goToStep3() {
        RelapseStep3Fragment step3 = RelapseStep3Fragment.newInstance();
        clock.stopAlarm();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step3).commit();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(clock);
    }

}
