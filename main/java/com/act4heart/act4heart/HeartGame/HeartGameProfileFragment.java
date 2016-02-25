package com.act4heart.act4heart.HeartGame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.act4heart.act4heart.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class HeartGameProfileFragment extends Fragment {

    private View view;

    public HeartGameProfileFragment() {
        // Required empty public constructor
    }

    public static HeartGameProfileFragment newInstance() {
        HeartGameProfileFragment fragment = new HeartGameProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.heart_game_profile_fragment, container, false);

        updatePlayerInfo();

        //Sets listener for button click to go to play menu
        Button playButton = (Button) view.findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNewGame();
            }
        });

        return view;
    }

    private void updatePlayerInfo() {
        JSONObject data = GameDatabaseHandler.retrievePlayerInfo();

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView level = (TextView) view.findViewById(R.id.level);
        TextView wins = (TextView) view.findViewById(R.id.wins);
        TextView expLeft = (TextView) view.findViewById(R.id.expLeft);

        try {
            title.setText((String)data.get("Title"));
            level.setText(String.valueOf(data.get("Level")));
            wins.setText(String.valueOf(data.get("Wins")));
            expLeft.setText("Poäng till nästa nivå: " + String.valueOf(data.get("ExpToLevel")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //not yet implemented TODO
    private void playNewGame() {

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
