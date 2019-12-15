package com.example.vksbermvvm.presentation;

import android.app.Application;

import com.vk.sdk.VKSdk;

public class MyApplication extends Application {

    /**
     * Инициализация VKSdk
     *
     * @author Цунский Роман on 2019-12-15
     */
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
