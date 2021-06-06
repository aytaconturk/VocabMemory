package com.example.vocabmemory;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(final Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel1");
        builder.setSmallIcon(R.drawable.ic_baseline_add_alert_24);
        builder.setContentTitle("Notication Test");
        builder.setContentText("lorem1 sdfko v fdvgfdo fdgdfog");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());

        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment()).addToBackStack(null).commit();

    }


}
