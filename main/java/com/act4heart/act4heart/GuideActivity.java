package com.act4heart.act4heart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ImageView image;
    private Integer currentImage;
    private ArrayList<Integer> imageNames;
    private Button previousButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);

        //Add toolbar
        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        previousButton = (Button) findViewById(R.id.btn_previous);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousImage();
            }
        });

        nextButton = (Button) findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });

        currentImage = 0;
        imageNames = new ArrayList<Integer>(){{add(R.drawable.pic1); add(R.drawable.pic2); add(R.drawable.pic3);}};
        image = (ImageView) findViewById(R.id.pictureHolder);

        setImage();
    }

    private void nextImage(){
        if(currentImage == 0){
            previousButton.setAlpha(1);
        } else if (currentImage == imageNames.size() - 2){
            nextButton.setAlpha(0.3f);
        }
        if(currentImage < imageNames.size() - 1){
            currentImage++;
            setImage();
        }
    }

    private void previousImage(){
        if(currentImage == imageNames.size() - 1){
            nextButton.setAlpha(1);
        } else if (currentImage == 1){
            previousButton.setAlpha(0.3f);
        }
        if(currentImage > 0){
            currentImage--;
            setImage();
        }
    }

    private void setImage(){
        image.setImageResource(imageNames.get(currentImage));
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
