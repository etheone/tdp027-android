package com.act4heart.act4heart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class RelapseLastPageFragment extends Fragment {
    private View view;

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

        return view;
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
