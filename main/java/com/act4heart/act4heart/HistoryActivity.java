package com.act4heart.act4heart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class HistoryActivity extends AppCompatActivity {

    Bundle state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        state = savedInstanceState;

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Historik");
        }

        //adds fragment to view
        HistoryListFragment historyListFragment = HistoryListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.historyContainer, historyListFragment, "historyFragment").addToBackStack("historyFragment").commit();
    }

    // OPTIONS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);
        MenuBarHandler.menuBarSetup(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnValue = MenuBarHandler.menuItemFunctionalityNoPopup(item, this);

        if(returnValue) return returnValue;
        return super.onOptionsItemSelected(item);
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
