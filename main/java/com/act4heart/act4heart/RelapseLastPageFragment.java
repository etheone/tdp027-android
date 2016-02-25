package com.act4heart.act4heart;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class RelapseLastPageFragment extends Fragment {
    private View view;
    private ImageView phoneIcon;

    private MyLocationListener myLocationListener;

    public RelapseLastPageFragment() {
        // Required empty public constructor
    }

    public static RelapseLastPageFragment newInstance() {
        RelapseLastPageFragment fragment = new RelapseLastPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.relapse_last_page_fragment, container, false);
        TextView positionText = (TextView) view.findViewById(R.id.positionText);

        myLocationListener = new MyLocationListener(this.getContext(),positionText);

        TextView startTimeStamp = (TextView) view.findViewById(R.id.activityStartText);
        startTimeStamp.setText(((RelapseProcessActivity) this.getActivity()).redClock.getSavedHHmmByKey("Start"));

        TextView lastTimeStamp = (TextView) view.findViewById(R.id.lastNitroText);
        lastTimeStamp.setText(((RelapseProcessActivity) this.getActivity()).redClock.getSavedHHmmByKey("Fourth"));


        //Makes phonebutton clickable
        phoneIcon = (ImageView)view.findViewById(R.id.phone_icon);
        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EmergencyCallHandler.isCallingEmergency) {
                    // End emergency call.
                    EmergencyCallHandler.endOngoingCall(getContext());
                    EmergencyCallHandler.isCallingEmergency = false;

                } else {
                    EmergencyCallHandler.isCallingEmergency = true;
                    makeCall();
                }
            }
        });

        return view;
    }

    private void makeCall() {
        phoneIcon = (ImageView)getActivity().findViewById(R.id.phone_icon);
        EmergencyCallHandler emergencyCallHandler = new EmergencyCallHandler(phoneIcon, (RelapseProcessActivity)getActivity());
        emergencyCallHandler.emergencyCall(StartMenu.EMERGENCY_PHONE_NUMBER);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //If we are leaving the activity we must destroy the location listener.
    public void onStop(){
        super.onStop();
        myLocationListener.terminateService();
    }

    //If the service is not running, we must restart it here
    public void onResume(){
        super.onResume();
        if(myLocationListener.isTerminated()){
            myLocationListener.startService();
        }
    }

}
