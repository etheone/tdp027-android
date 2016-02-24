package com.act4heart.act4heart;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class RelapseLastPageFragment extends Fragment {
    private View view;
    private ImageView phoneIcon;
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

        MyLocationListener myLocationListener = new MyLocationListener(this.getContext(),positionText);

        TextView startTimeStamp = (TextView) view.findViewById(R.id.activityStartText);
        startTimeStamp.setText(((RelapseProcessActivity) this.getActivity()).redClock.getSavedHHmmByKey("Start"));

        TextView lastTimeStamp = (TextView) view.findViewById(R.id.lastNitroText);
        lastTimeStamp.setText(((RelapseProcessActivity) this.getActivity()).redClock.getSavedHHmmByKey("Fourth"));

        //Makes phonebutton clickable
        phoneIcon = (ImageView)view.findViewById(R.id.phone_icon);
        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        return view;
    }

    private void makeCall() {
        phoneIcon = (ImageView)getActivity().findViewById(R.id.phone_icon);
        EmergencyCallHandler emergencyCallHandler = new EmergencyCallHandler(phoneIcon, (RelapseProcessActivity)getActivity());
        emergencyCallHandler.emergencyCall("0739474140");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
