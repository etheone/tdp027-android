package com.act4heart.act4heart;

import android.app.ExpandableListActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeartFailureInfoActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heart_failure_info_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Om hjärtinfarkt");
        }

        expListView = (ExpandableListView)findViewById(R.id.heartfailure_expandable_list_view);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    /*
    * Preparing the list data
    */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Behandlingsprocessen");
        listDataHeader.add("Vad händer i kroppen?");
        listDataHeader.add("Ligger jag i riskzonen?");
        listDataHeader.add("Vad säger forskningen?");

        // Adding child data
        List<String> treatmentProcess = new ArrayList<String>();
        treatmentProcess.add("Här testar vi att skriva en jätte lång radHär testar vi att skriva en jätte lång radHär testar vi att skriva en jätte lång radHär testar vi att skriva en jätte lång rad.");
        treatmentProcess.add("List test");

        List<String> bodyInfo = new ArrayList<String>();
        bodyInfo.add("kroppsinformation!");

        List<String> riskZone = new ArrayList<String>();
        riskZone.add("En massa risker!");

        List<String> research = new ArrayList<String>();
        research.add("Forskning!");

        listDataChild.put(listDataHeader.get(0), treatmentProcess); // Header, Child data
        listDataChild.put(listDataHeader.get(1), bodyInfo);
        listDataChild.put(listDataHeader.get(2), riskZone);
        listDataChild.put(listDataHeader.get(3), research);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);

        if(StartMenu.soundOn) {
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_up);
        }
        else{
            ((MenuItem)menu.findItem(R.id.action_sound)).setIcon(R.drawable.ic_volume_off);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_sound) {
            if(StartMenu.soundOn) {
                item.setIcon(R.drawable.ic_volume_off);
                StartMenu.soundOn = false;
            }
            else{
                item.setIcon(R.drawable.ic_volume_up);
                StartMenu.soundOn = true;
            }
            StartMenu.prefs.edit().putBoolean("soundOn", StartMenu.soundOn).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
