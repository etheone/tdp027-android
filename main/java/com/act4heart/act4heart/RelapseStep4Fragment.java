package com.act4heart.act4heart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link RelapseStep4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep4Fragment extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.relapse_step4_fragment, container, false);
    }

}
