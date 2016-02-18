package com.act4heart.act4heart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by eried975 on 2016-02-18.
 */
public class HistoryListAdapter extends ArrayAdapter<HistoryEntry> {
    public HistoryListAdapter(Context context, ArrayList<HistoryEntry> entryList){
        super(context, 0, entryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        HistoryEntry historyEntry = getItem(position);

        historyEntry.
    }
}
