package com.example.sharedpreference_login;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Struct;

public class DashboardActivity extends AppCompatActivity {

    FloatingActionButton fab;
    SharedPreferenceFile sp;
    String user;
    TextView tv;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sp = new SharedPreferenceFile(DashboardActivity.this);
        fab = findViewById(R.id.fab);
        tv = findViewById(R.id.tv);

        user = sp.getName();
        Log.d("mydata", user);
        tv.setText(user);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.writeLoginStatus(false);
                sp.clearData();
                Intent i = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
