package com.example.romer.mucontact;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;


/**
 * Created by romer on 4/7/2017.
 */

public class MuContactApp extends SugarApp {
    private static MuContactApp instance;

    public MuContactApp() {
        super();
        instance = this;
    }

    public static MuContactApp getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

}
