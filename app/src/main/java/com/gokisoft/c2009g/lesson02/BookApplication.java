package com.gokisoft.c2009g.lesson02;

import android.app.Application;

import com.gokisoft.c2009g.lesson04.DBHelper;

public class BookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DataMgr.getInstance();
//        DBHelper.getInstance(getApplicationContext());
    }
}
