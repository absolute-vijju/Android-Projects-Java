package com.example.avloading;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(MainActivity.this);
        avLoadingIndicatorView.setIndicator("BallSpinFadeLoader");
        avLoadingIndicatorView.setIndicatorColor(Color.BLACK);

// Create progress dialog
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setIndeterminate(true);
        pd.show();
// Set custom view(Loading Indicator View) after showing dialog
        pd.setContentView(avLoadingIndicatorView);*/
    }
}
