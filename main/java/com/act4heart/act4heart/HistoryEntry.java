package com.act4heart.act4heart;

import android.util.Log;

//Class to hold the information about an emergency situation
public class HistoryEntry {
    private String start = " -";
    private String second = " -";
    private String third = " -";
    private int amountTaken = 0;

    public HistoryEntry(String _start, String _second, String _third){
        if(_start != ""){
            start = _start;
            amountTaken += 1;
        }

        if(_second != ""){
            second = _second;
            amountTaken += 1;
        }

        if(_third != ""){
            third = _third;
            amountTaken += 1;
        }
    }

    public String getStart(){
        return start;
    }

    public String getSecond(){
        return second;
    }

    public String getThird(){
        return third;
    }

    public int getAmountTaken(){
        return amountTaken;
    }
}
