package com.gokisoft.c2009g.lesson02;

import android.app.Application;

public class BookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DataMgr.getInstance();
    }
}
