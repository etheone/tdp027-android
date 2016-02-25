package com.act4heart.act4heart.HeartGame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public final class GameDatabaseHandler {
    private GameDatabaseHandler(){
    }

    //TODO Get data from database instead of locally
    public static JSONObject retrievePlayerInfo(){
        JSONObject data = new JSONObject();
        try {
            data.put("UserID", "Kalle")
                    .put("DeviceID", "ab55wj5")
                    .put("Wins", 522)
                    .put("Exp", 2200);

            data.put("Level", calcLevel(((int) data.get("Exp"))));
            data.put("ExpToLevel", expToLevel(((int) data.get("Exp"))));
            data.put("Title", getTitle(((int) data.get("Level"))));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return data;
    }

    private static String getTitle(int level) {

        if( level < 5) {
            return "Nybörjare";
        } else if ( level < 10) {
            return "Svensson";
        } else if ( level < 15 ) {
            return "Expert";
        } else {
            return "Hjärtkirurg";
        }
    }

    private static int calcLevel(int exp){
        return (exp/100);
    }

    private static int expToLevel(int exp) {
        return 100 - (exp/100);
    }
}
