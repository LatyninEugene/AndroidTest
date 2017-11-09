package com.latynin.testproject;

import android.app.Application;

import com.vk.sdk.VKSdk;

public class Main extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
