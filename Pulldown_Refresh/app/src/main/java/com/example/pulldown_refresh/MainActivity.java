package com.example.pulldown_refresh;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ListView lv;
    TextView tv;
    int index = 0;
    ArrayAdapter arrayAdapter;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.ref);
        tv = findViewById(R.id.tvtitle);
        lv = findViewById(R.id.lv);
        ll = findViewById(R.id.ll);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (index) {
                    case 0:
                        ll.setBackgroundColor(getResources().getColor(R.color.color0));
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fruits_names));
                        lv.setAdapter(arrayAdapter);
                        tv.setText("Fruit Names");
                        index++;
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 1:
                        ll.setBackgroundColor(getResources().getColor(R.color.color1));
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bird_names));
                        lv.setAdapter(arrayAdapter);
                        tv.setText("Bird Names");
                        index++;
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 2:
                        ll.setBackgroundColor(getResources().getColor(R.color.color2));
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.vegetable_names));
                        lv.setAdapter(arrayAdapter);
                        tv.setText("Vegetable Names");
                        index++;
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 3:
                        ll.setBackgroundColor(getResources().getColor(R.color.color3));
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.animal_names));
                        lv.setAdapter(arrayAdapter);
                        tv.setText("Animal Names");
                        index++;
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 4:
                        ll.setBackgroundColor(getResources().getColor(R.color.color4));
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sweets_names));
                        lv.setAdapter(arrayAdapter);
                        tv.setText("Sweets Names");
                        index++;
                        swipeRefreshLayout.setRefreshing(false);
                        break;

                }

                if (index > 4) {
                    index = 0;
                }


            }
        });


    }
}
