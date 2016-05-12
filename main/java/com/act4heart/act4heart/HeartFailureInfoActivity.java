package com.act4heart.act4heart;

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
        listDataHeader.add("Riskfaktorer");


        // Adding child data
        List<String> treatmentProcess = new ArrayList<String>();
        treatmentProcess.add("Vid en hjärtinfarkt kan en skada uppkomma som gör att hjärtat inte klarar att arbeta lika bra som innan. "
                +"Hjärtinfarkt kan vara en livshotande sjukdom och kräver omedelbar sjukhusvård. Ju tidigare du får vård desto större är möjligheten att behandla och påverka hjärtinfarktens utveckling.\n\n"
                +"Om du misstänker att någon i din närhet fått en hjärtinfarkt, ring 112.\n\n"
                +"Vid en akut hjärtinfarkt frisätts stresshormoner ut i blodbanan som leder till förhöjt blodtryck och snabbare hjärtrytm. Detta stressar hjärtat ytterligare och ökar den farliga syrebristen. "
                +"Om du fått en hjärtinfarkt behöver kranskärlet som har täppts till av en blodpropp öppnas så snabbt som möjligt så att blodet kan passera normalt igen."
                +"Det kan ske med en så kallad ballongvidgning av kärlen eller med blodproppslösande medicin. Du får också behandling med andra typer av läkemedel efter utskrivningen.\n\n"
                +"Ju tidigare kärlet öppnas, desto större är chansen att blodflödet till hjärtmuskeln återställs och hjärtskadan minimeras. "
                +"Om du bor i närheten av ett sjukhus som kan göra akut ballongvidgning brukar ambulansen köra direkt dit om EKG-bilden uppvisar ett särskilt mönster. "
                +"Om du befinner dig mer än två timmar från ett sådant sjukhus kan du få propplösande medicin, som kan ges redan i hemmet eller i ambulansen av en sjuksköterska, som följt med. ");

        List<String> bodyInfo = new ArrayList<String>();
        bodyInfo.add("Om du är äldre eller har diabetes och får hjärtinfarkt, är det inte säkert "+
                        "att du får ont i bröstet. Symtomen kan då vara diffusa, som andnöd eller "+
                        "stark trötthet. Smärtan kan vara mindre intensiv och kan misstolkas som "+
                        "mindre allvarlig.\n\n-Ihållande bröstsmärta\n" +
                "-Obehagskänsla i bröstet\n" +
                "-Strålande i hals, käkar eller skuldror\n" +
                "-Illamående\n" +
                "-Viktigt att inte ta nitroglycerin om du är illamående \n" +
                "-Andnöd\n" +
                "-Kallsvettning\n" +
                "-Rädsla och ångest\n" +
                "-Värk i ryggen\n" +
                "-Hjärtklappning och yrsel\n" +
                "-Influensaliknande besvär");

        List<String> riskZone = new ArrayList<String>();
        riskZone.add("Bröstsmärtor är en av de vanligaste anledningarna att söka akut sjukhusvård. "+
                "Att du har ont i bröstet behöver inte betyda att du har ett hjärtproblem. De kan "+
                "uppstå på grund av många andra orsaker, såsom:\n\n-Irritation av slemhinnan i "+
                "nedre delen av matstrupen\n" +
                "-Sjukdomar i magsäck och gallblåseväggen\n" +
                "-Sjukdomar i lungan\n" +
                "-Besvär i bröstkorgen eller överkroppens muskler\n" +
                "-Infektioner.");

        List<String> riskFactors = new ArrayList<String>();
        riskFactors.add("Hjärt- och kärlsjukdomar är den vanligaste dödsorsaken i Sverige."
                +"Ungefär hälften av västvärldens befolkning insjuknar och dör i någon av dessa sjukdomar, "
                +"där akut kranskärlssjukdom, alltså instabil kärlkramp och hjärtinfarkt, utgör en stor del.\n\n"
                +"Det är känt att vissa typiska riskfaktorer ökar risken för att få hjärtinfarkt. "
                +"De flesta av dessa kan du påverka med ändrade vanor. Risken ökar också med åldern.\n\n"
                +"-Sluta med rökning och annat tobaksbruk. Även om du redan har blivit sjuk finns stora hälsovinster med att sluta röka.\n\n" +
                "-Minska det skadliga kolesterolet (LDL) genom ändrad kost. Höga blodfetter ökar risken för att fååderförfettning, vilket i sin tur ökar risken för hjärtinfarkt.\n\n" +
                "-Håll din diabetessjukdom under kontroll, diabetes typ 1 eller diabetes typ 2. Två av tre personer som får en hjärtinfarkt har en försämrad omsättning av socker i kroppen.\n\n" +
                "-Högt blodtryck ökar risken för hjärtinfarkt. Du kan ofta sänka blodtrycket med fysisk aktivitet eller läkemedel. \n\n" +
                "-Motionera regelbundet. En daglig promenad på 30 minuter är bra motion för att minska risken för att få en kranskärlssjukdom.");


        listDataChild.put(listDataHeader.get(0), treatmentProcess); // Header, Child data
        listDataChild.put(listDataHeader.get(1), bodyInfo);
        listDataChild.put(listDataHeader.get(2), riskZone);
        listDataChild.put(listDataHeader.get(3), riskFactors);


    }

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
}
