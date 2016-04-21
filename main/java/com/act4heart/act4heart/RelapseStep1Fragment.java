package com.act4heart.act4heart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RelapseStep1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep1Fragment extends Fragment {

    private ImageView phoneIcon;
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


        phoneIcon = (ImageView)v.findViewById(R.id.phone_icon);
        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EmergencyCallHandler.isCallingEmergency) {
                    // End emergency call.
                    EmergencyCallHandler.endOngoingCall(getContext());
                    EmergencyCallHandler.isCallingEmergency= false;

                } else {
                    EmergencyCallHandler.isCallingEmergency= true;
                    makeCall();
                }
            }
        });


        //Saves the first timestamp for this emergency
        ((RelapseProcessActivity)this.getActivity()).redClock.saveStartTime();

        // Inflate the layout for this fragment
        return v;
    }

    private void makeCall() {
        phoneIcon = (ImageView)getActivity().findViewById(R.id.phone_icon);
        EmergencyCallHandler emergencyCallHandler = new EmergencyCallHandler(phoneIcon, (RelapseProcessActivity)getActivity());
        emergencyCallHandler.emergencyCall(StartMenuActivity.EMERGENCY_PHONE_NUMBER);
    }

    public void goToStep2() {
        RelapseStep2Fragment step2 = RelapseStep2Fragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step2).commit();
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }
}
