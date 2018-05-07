package com.example.mateusz.calculateandroid;

import android.os.Debug;

/**
 * Created by Mateusz on 2017-03-28.
 */

public class ClickOperatorTimer {

    private int clickTimer = 0;
    private String name;

    public ClickOperatorTimer(String sName){

        this.name = sName;

    }
    public int incrementTimer(){
        return this.clickTimer++;
    }

    public int getClickTimer() {
        return clickTimer;
    }

    public void setClickTimer(int clickTimer) {
        this.clickTimer = clickTimer;
    }
}
