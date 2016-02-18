package com.act4heart.act4heart;

import android.util.Log;

public class HistoryEntry {
    private String start = "";
    private String second = "";
    private String third = "";
    private int amountTaken = 0;

    public HistoryEntry(String _start, String _second, String _third){
        if(_start != ""){
            start = _start;
            amountTaken += 1;
        } else {
            start = "-";
        }

        if(_second != ""){
            second = _second;
            amountTaken += 1;
        } else {
            second = "-";
        }

        if(_third != ""){
            third = _third;
            amountTaken += 1;
        } else {
            third = "-";
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
