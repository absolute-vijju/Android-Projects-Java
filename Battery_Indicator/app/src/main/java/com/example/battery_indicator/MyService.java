package com.example.battery_indicator;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class MyService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int b_level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        Toast.makeText(context, "" + b_level, Toast.LENGTH_SHORT).show();
    }
}
