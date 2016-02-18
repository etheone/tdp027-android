package com.act4heart.act4heart;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryListFragment extends Fragment {

    private View view;
    public HistoryListFragment() {
        // Required empty public constructor
    }


    public static HistoryListFragment newInstance() {
        HistoryListFragment fragment = new HistoryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_list_fragment, container, false);

        //calls functions to fetch saved data and add it to list
        JSONArray savedDataArray = getSavedData();
        addDataToEntry(savedDataArray);

        return view;
    }

    //gets saved data from the phone
    private JSONArray getSavedData(){
        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                "timerData", 0);

        //prefs.edit().clear().commit();

        String savedData = prefs.getString("timerData", null);

        JSONArray savedDataArray = null;

        if(savedData != null){
            //Converts the string data we just retrieved into an JSONArray
            try {
                savedDataArray = new JSONArray(savedData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

            //Makes sure we don't save a null JSONArray
            savedDataArray = new JSONArray();
        }

        return savedDataArray;
    }

    //uses the fetched data to add it into the list view
    private void addDataToEntry(JSONArray savedDataArray){
        ListView historyListView = (ListView)view.findViewById(R.id.historyListView);

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Fetches the entry and sends it when we switch to the new fragment
                HistoryEntry entry = (HistoryEntry) parent.getItemAtPosition(position);

                HistoryEntryFragment historyEntryFragment = HistoryEntryFragment.newInstance();
                historyEntryFragment.updateHistoryEntry(entry);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.historyContainer, historyEntryFragment, "historyEntryFragment")
                        .addToBackStack("historyEntryFragment")
                        .commit();

            }
        });

        ArrayList<HistoryEntry> historyEntries = new ArrayList<>();
        HistoryListAdapter historyAdapter = new HistoryListAdapter(this.getContext(), historyEntries);

        //makes an entry for each saved emergency and adds it to the adapter
        for(int i = 0; i < savedDataArray.length(); i++){
            try {
                JSONObject object = (JSONObject)savedDataArray.get(i);
                String start = (String) object.get("Start");
                start = timeStampToDate(start);
                String second = (String) object.get("Second");
                second = timeStampToDate(second);
                String third = (String) object.get("Third");
                third = timeStampToDate(third);
                HistoryEntry entry = new HistoryEntry(start,second,third);
                historyAdapter.add(entry);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        historyListView.setAdapter(historyAdapter);
    }

    //converts simple string to correct format
    private String timeStampToDate(String timeStamp){
        String[] temp = timeStamp.split(",");
        if (temp.length == 6){
            timeStamp = temp[0] + "-" + temp[1] + "-" + temp[2] + " " + temp[3] + ":" + temp[4] + ":" + temp[5];
        } else {
            timeStamp = "";
        }
        return timeStamp;
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
