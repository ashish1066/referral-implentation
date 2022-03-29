package com.carselonadaily.carselonacustomer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import carselonadaily.carselonacustomer.R;
import theflyy.com.flyy.Flyy;
import theflyy.com.flyy.helpers.OnFlyyTaskComplete;

public class MainActivity extends AppCompatActivity {
    private EditText username,mobile;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.edit_text_name) ;
        mobile = (EditText)findViewById(R.id.edit_text_mobile) ;
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Message", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d("TOKEN ::", token);
                    }
                });
        initSdk();
    }

    public void initSdk(){
        Flyy.setPackageName("<Package-Name>");
        Flyy.init(this.getApplicationContext(), "PartnerId", Flyy.STAGE);
        Flyy.setThemeColor("#00FF00","#00FF00");
    }

    public void onClickLogin(View view){
        System.out.println(mobile.getText().toString());
        if(!username.getText().toString().equals("") && !mobile.getText().toString().equals("")){
            Flyy.setUser(mobile.getText().toString(), new OnFlyyTaskComplete() {
                @Override
                public void onComplete() {
                    Flyy.setUsername(username.getText().toString());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

        //Flyy.navigateToOffersActivity(this.getApplicationContext());
        //System.out.println("Button Clicked");
    }
    public void showNotification(){
        NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(this,"MYCHANNEL")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Notification!") // title for notification
                .setContentText("Hello word") // message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(this, MainActivity.class);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }
}