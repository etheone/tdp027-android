package com.act4heart.act4heart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HistoryEntryFragment extends Fragment {

    View view;
    HistoryEntry historyEntry;

    public HistoryEntryFragment() {
        // Required empty public constructor
    }

    public static HistoryEntryFragment newInstance() {
        HistoryEntryFragment fragment = new HistoryEntryFragment();

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
        view = inflater.inflate(R.layout.history_entry_fragment, container, false);

        //fetches all the views and writes text to them
        TextView historyTitle = (TextView) view.findViewById(R.id.entryTitle);
        TextView historyStart = (TextView) view.findViewById(R.id.startNitro);
        TextView historySecond = (TextView) view.findViewById(R.id.secondNitro);
        TextView historyThird = (TextView) view.findViewById(R.id.thirdNitro);
        TextView historyTotal = (TextView) view.findViewById(R.id.totalAmount);

        historyTitle.setText("Akutsituation " + historyEntry.getStart().split(" ")[0]);
        historyStart.setText(historyEntry.getStart().split(" ")[1]);
        historySecond.setText(historyEntry.getSecond().split(" ")[1]);
        historyThird.setText(historyEntry.getThird().split(" ")[1]);
        historyTotal.setText(String.valueOf(historyEntry.getAmountTaken()));

        return view;
    }

    public void updateHistoryEntry(HistoryEntry _historyEntry){
        historyEntry = _historyEntry;
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
