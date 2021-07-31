package com.example.sharedpreference_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferenceFile sp;
    EditText username;
    Button btnlogin;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etusername);
        btnlogin = findViewById(R.id.btnlogin);

        sp = new SharedPreferenceFile(MainActivity.this);

        if (sp.readLoginStatus()) {
            Intent i = new Intent(this, DashboardActivity.class);
            startActivity(i);
            finish();
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                sp.writeLoginStatus(true);
                sp.writeName(name);
                Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
