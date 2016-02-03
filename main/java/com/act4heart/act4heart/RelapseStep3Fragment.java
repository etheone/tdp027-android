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
 * Use the {@link RelapseStep3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelapseStep3Fragment extends Fragment {


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
        View v = inflater.inflate(R.layout.relapse_step3_fragment, container, false);
        Button btn = (Button) v.findViewById(R.id.btn_to_step4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStep4();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    public void goToStep4() {
        RelapseStep4Fragment step4 = RelapseStep4Fragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step4).commit();
    }


}
