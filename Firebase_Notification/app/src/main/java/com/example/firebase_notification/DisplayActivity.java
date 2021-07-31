package com.example.firebase_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView tv;
    String title, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tv = findViewById(R.id.tv);

        if (getIntent().getStringExtra("title") != null && getIntent().getStringExtra("body") != null) {
            title = getIntent().getStringExtra("title");
            body = getIntent().getStringExtra("body");
            tv.setText("Title: " + title + "\nBody: " + body);
        } else {
            tv.setText("No notification data found");
        }

    }
}
