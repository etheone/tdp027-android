package com.act4heart.act4heart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//Custom list adapter for history entries
public class HistoryListAdapter extends ArrayAdapter<HistoryEntry> {
    public HistoryListAdapter(Context context, ArrayList<HistoryEntry> entryList){
        super(context, 0, entryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        HistoryEntry historyEntry = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_entry_view, parent, false);
        }

        TextView startText = (TextView) convertView.findViewById(R.id.historyStart);

        //Splits so that we only writes out date and not time
        startText.setText("Akutsituation: " + historyEntry.getStart().split(" ")[0]);


        return convertView;
    }
}
