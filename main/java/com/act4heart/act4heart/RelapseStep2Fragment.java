package com.act4heart.act4heart;

import android.content.Context;
import android.net.Uri;
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
                goToStep3();
            }
        });

        // Activate blue clock.
        BlueClockFragment clock = BlueClockFragment.newInstance();
        clock.setTimer(300);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step2, clock).commit();

        //Activate red clock
        RedClockFragment redClock = RedClockFragment.newInstance();

        //Sets the new timestamp for the red clock
        redClock.saveNewTimeStamp();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.clock_container2_step2, redClock).commit();

        // Inflate the layout for this fragment
        return v;
    }

    public void goToStep3() {
        RelapseStep3Fragment step3 = RelapseStep3Fragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step3).commit();
    }



}
