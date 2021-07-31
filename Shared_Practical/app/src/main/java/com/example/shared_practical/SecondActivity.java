package com.example.shared_practical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {

    TextView tvusername;
    Button btnlogout, btnhome;
    HashMap hm;
    MySharedPreferenceFile mysp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvusername = findViewById(R.id.tvusername);
        btnlogout = findViewById(R.id.btnlogout);
        btnhome = findViewById(R.id.btnhome);

        mysp = new MySharedPreferenceFile(SecondActivity.this);
        hm = mysp.getData();

        String result = (String) hm.get(MySharedPreferenceFile.keyData);
        tvusername.setText(result);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysp.clearData();
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
