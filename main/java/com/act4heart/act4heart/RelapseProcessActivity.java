package com.act4heart.act4heart;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RelapseProcessActivity extends AppCompatActivity {


    //If the timer has run out
    public Boolean canProceed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relapse_process);

        //RelapseStep1Fragment step1 = RelapseStep1Fragment.newInstance();
        // getSupportFragmentManager().beginTransaction()
       // .replace(R.id.fragment_container, gMapFragment).commit();

        //getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, step1).commit();
        switchFragment(1);
    }

    public void switchFragment(int nextFragment){
        Fragment fragment = null;
        if(nextFragment == 1){
            fragment = RelapseStep1Fragment.newInstance();
        } else if (nextFragment == 2) {
            fragment= RelapseStep2Fragment.newInstance();
        } else if (nextFragment == 3) {
            fragment= RelapseStep2Fragment.newInstance();
        } else if (nextFragment == 4) {
            fragment= RelapseStep2Fragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.relapse_fragment_container, fragment).commit();

    }
}
