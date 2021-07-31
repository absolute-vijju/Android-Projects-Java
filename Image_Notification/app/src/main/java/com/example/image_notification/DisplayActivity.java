package com.example.image_notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tv = findViewById(R.id.tv);

        String title = getIntent().getStringExtra("title");
        String message = getIntent().getStringExtra("message");

        tv.setText("Title: " + title + "\n Message: " + message);


    }
}
