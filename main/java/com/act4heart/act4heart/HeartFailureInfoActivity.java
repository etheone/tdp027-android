package com.act4heart.act4heart;

import android.app.ExpandableListActivity;
import android.content.Intent;
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
        listDataHeader.add("Hjärtinfarkt");
        listDataHeader.add("Vaga symptom kan också vara infarkt");
        listDataHeader.add("Bröstsmärtor kan vara annat än infarkt");

        // Adding child data
        List<String> treatmentProcess = new ArrayList<String>();
        treatmentProcess.add("En hjärtinfarkt beror oftast på att en blodpropp har bildats som helt"+
                "eller delvis täpper till hjärtats kranskärl så att blodet inte kan passera som det"+
                "brukar. Den del av hjärtat som skulle ha tagit emot blodet från det tilltäppta"+
                "kärlet drabbas av syrebrist och hjärtmuskeln skadas." +
                "\n\nVid en hjärtinfarkt kan en skada uppkomma som gör att hjärtat inte"+
                "klarar att arbeta lika bra som innan. Hjärtinfarkt kan vara en livshotande"+
                "sjukdom och kräver omedelbar sjukhusvård. Ju tidigare du får vård desto"+
                "större är möjligheten att behandla och påverka hjärtinfarktens utveckling." +
                "Om du misstänker att någon i din närhet fått en hjärtinfarkt, ring 112.");

        List<String> bodyInfo = new ArrayList<String>();
        bodyInfo.add("Om du är äldre eller har diabetes och får hjärtinfarkt, är det inte säkert "+
                        "att du får ont i bröstet. Symtomen kan då vara diffusa, som andnöd eller "+
                        "stark trötthet. Smärtan kan vara mindre intensiv och kan misstolkas som "+
                        "mindre allvarlig.");

        List<String> riskZone = new ArrayList<String>();
        riskZone.add("Bröstsmärtor är en av de vanligaste anledningarna att söka akut sjukhusvård. "+
                "Att du har ont i bröstet behöver inte betyda att du har ett hjärtproblem. De kan "+
                "uppstå på grund av många andra orsaker, såsom:\n\n-Irritation av slemhinnan i "+
                "nedre delen av matstrupen\n" +
                "-Sjukdomar i magsäck och gallblåseväggen\n" +
                "-Sjukdomar i lungan\n" +
                "-Besvär i bröstkorgen eller överkroppens muskler\n" +
                "-Infektioner.");


        listDataChild.put(listDataHeader.get(0), treatmentProcess); // Header, Child data
        listDataChild.put(listDataHeader.get(1), bodyInfo);
        listDataChild.put(listDataHeader.get(2), riskZone);
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

        if (id == R.id.home_button) {
            Intent homeAcitivity = new Intent(this, StartMenu.class);
            homeAcitivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeAcitivity);
        }

        return super.onOptionsItemSelected(item);
    }
}
