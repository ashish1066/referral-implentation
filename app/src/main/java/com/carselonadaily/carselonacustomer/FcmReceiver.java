package com.carselonadaily.carselonacustomer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import carselonadaily.carselonacustomer.R;
import theflyy.com.flyy.helpers.FlyyNotificationHandler;


public class FcmReceiver extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        if(data.containsKey("notification_source")
                && data.get("notification_source").equalsIgnoreCase("flyy_sdk")) {
            FlyyNotificationHandler.handleNotification(getApplicationContext(),     remoteMessage,null, null);
        }
        //showNotification();
        System.out.println(remoteMessage.getData());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotification(){
        notificationInit();
        NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(this,"MYCHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_background) // notification icon
                .setContentTitle("Notification!") // title for notification
                .setContentText("Hello word") // message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notificationInit(){
        int importance = NotificationManager.IMPORTANCE_HIGH; //Important for heads-up notification
        NotificationChannel channel = new NotificationChannel("MYCHANNEL", "app", importance);
        channel.setDescription("Description");
        channel.setShowBadge(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
