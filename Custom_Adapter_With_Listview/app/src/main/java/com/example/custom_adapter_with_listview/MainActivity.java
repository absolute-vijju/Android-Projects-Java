package com.example.custom_adapter_with_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    int img[] = {R.mipmap.logo1, R.mipmap.logo2};
    String title[] = {"1st Logo", "2nd Logo"};
    ArrayList<GetterSetter> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        for (int i = 0; i < img.length; i++) {
            GetterSetter gl = new GetterSetter(img[i], title[i]);
            al.add(gl);
        }

        MyAdapter my = new MyAdapter(MainActivity.this, al);
        lv.setAdapter(my);
    }
}
