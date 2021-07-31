package com.example.firebase_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

 /*   TextView tv;
    String title, body;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


/*        tv = findViewById(R.id.tv);

        if (getIntent().getStringExtra("title") != null && getIntent().getStringExtra("body") != null) {
            title = getIntent().getStringExtra("title");
            body = getIntent().getStringExtra("body");
            tv.setText("Title: " + title + "\nBody: " + body);
        } else {
            tv.setText("No notification data found");
        }*/

    }
}
