package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("mydata", "Activity --> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("mydata", "Activity --> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mydata", "Activity --> onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("mydata", "Activity --> onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("mydata", "Activity --> onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("mydata", "Activity --> onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mydata", "Activity --> onDestroy()");
    }
}
