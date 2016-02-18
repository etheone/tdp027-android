package com.act4heart.act4heart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class HistoryActivity extends AppCompatActivity {

    Bundle state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        state = savedInstanceState;

        //adds fragment to view
        HistoryListFragment historyListFragment = HistoryListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.historyContainer, historyListFragment, "historyFragment").addToBackStack("historyFragment").commit();
    }

    //overrides the back press button and finds the fragments so new one can be removed and old one replaces it
    @Override
    public void onBackPressed(){

        //fetches fragments by tag
        HistoryEntryFragment historyEntryFragment = (HistoryEntryFragment)getSupportFragmentManager().findFragmentByTag("historyEntryFragment");
        HistoryListFragment historyListFragment = (HistoryListFragment) getSupportFragmentManager().findFragmentByTag("historyFragment");
        if(historyEntryFragment != null) {

            //removes new fragment from backstack, replaces the container with the old fragment and removes the new fragment
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, historyListFragment, "historyFragment").addToBackStack("historyFragment")
                    .remove(historyEntryFragment)
                    .commit();


        } else {
            //ends activity
            finish();
        }
    }


}
