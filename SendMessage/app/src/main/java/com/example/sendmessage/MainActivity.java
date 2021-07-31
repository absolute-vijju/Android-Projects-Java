package com.example.sendmessage;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnsend;
    EditText etnumber, etmessage;
    String number, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnumber = findViewById(R.id.etnumber);
        etmessage = findViewById(R.id.etmessage);
        btnsend = findViewById(R.id.btnsebd);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = etnumber.getText().toString();
                message = etmessage.getText().toString();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = (PendingIntent) PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{i}, 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, message, pendingIntent, null);
                Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
