package com.example.customtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class contactus extends AppCompatActivity {

    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        tool = findViewById(R.id.mytoolbar);
        setSupportActionBar(tool);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*getSupportActionBar().setDisplayShowHomeEnabled(false);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tool.setTitle("Contact us");
    }
}
