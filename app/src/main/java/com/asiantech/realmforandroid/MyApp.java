package com.asiantech.realmforandroid;

import android.app.Application;

import com.asiantech.realmforandroid.network.core.ApiClient;
import com.asiantech.realmforandroid.network.core.ApiConfig;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by honhattan on 5/9/16.
 */
public class MyApp extends Application {
    private static MyApp sInstance = null;

    public static synchronized MyApp getInstance() {
        if (sInstance == null) {
            sInstance = new MyApp();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Setup Api client
        ApiConfig apiConfig = ApiConfig.builder(getApplicationContext())
                .setBaseUrl("https://t-api.xaas.jp/haisha-yoyaku/v1/")
                .build();
        ApiClient.getInstance().init(apiConfig);


    }
}
