package com.example.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("mydata", "OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("mydata", "OnStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("mydata", "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mydata", "OnResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("mydata", "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mydata", "OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("mydata", "OnRestart");
    }
}
