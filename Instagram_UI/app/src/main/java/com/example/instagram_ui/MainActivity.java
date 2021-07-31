package com.example.instagram_ui;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btm;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btm = findViewById(R.id.btm);
        frameLayout = findViewById(R.id.layout);

        btm.setSelectedItemId(R.id.home);

        btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.home:
                        menuItem.setChecked(true);
                        frameLayout.setBackgroundColor(Color.RED);
                        break;
                    case R.id.search:
                        menuItem.setChecked(true);
                        frameLayout.setBackgroundColor(Color.GREEN);
                        break;
                    case R.id.add:
                        menuItem.setChecked(true);
                        frameLayout.setBackgroundColor(Color.BLUE);
                        break;
                    case R.id.like:
                        menuItem.setChecked(true);
                        frameLayout.setBackgroundColor(Color.CYAN);
                        break;
                    case R.id.profile:
                        menuItem.setChecked(true);
                        frameLayout.setBackgroundColor(Color.MAGENTA);
                        break;
                }
                return false;
            }
        });
    }
}
