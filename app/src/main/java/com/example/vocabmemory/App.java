package com.example.vocabmemory;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {


    public static final String CHANNEL_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();

//        createNotificationChannel();
    }

}
