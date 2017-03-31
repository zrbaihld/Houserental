package com.zrb.baseapp.base;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppManager.setApplicationContext(this);
    }


}
