package com.example.efrainmg90.userloginparse.applications;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Efrainmg90 on 29/04/2017.
 */

public class AppParse extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);
    }
}
