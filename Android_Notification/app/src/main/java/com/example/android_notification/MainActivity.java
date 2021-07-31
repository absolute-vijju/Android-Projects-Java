package com.example.android_notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar.set(Calendar.HOUR_OF_DAY,12);
        calendar.set(Calendar.MINUTE,19);
        calendar.set(Calendar.SECOND,00);

        Intent i=new Intent(MainActivity.this,MyReceiver.class);

        PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this,1,i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am= (AlarmManager) getSystemService(ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
    }
}
