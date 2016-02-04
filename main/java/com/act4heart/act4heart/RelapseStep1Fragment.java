package com.act4heart.act4heart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RelapseStep1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep1Fragment extends Fragment {


    public RelapseStep1Fragment() {
        // Required empty public constructor
    }


    public static RelapseStep1Fragment newInstance() {

        return new RelapseStep1Fragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.relapse_step1_fragment, container, false);
        Button btn = (Button) v.findViewById(R.id.btn_to_step2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStep2();
            }
        });

        // Activate blue clock.
        BlueClockFragment clock = BlueClockFragment.newInstance();
        clock.setTimer(5);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.clock_container_step1, clock).commit();

        // Inflate the layout for this fragment
        return v;
    }

    public void goToStep2() {
        RelapseStep2Fragment step2 = RelapseStep2Fragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step2).commit();
    }

}
