package com.yy.x;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/1/22.
 */

public class ZApplication extends Application {
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
    }

    public static Context getAppContext(){
        return applicationContext;
    }
}
