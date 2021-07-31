package com.example.flagapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    int img[] = {R.mipmap.in, R.mipmap.br, R.mipmap.ca, R.mipmap.au, R.mipmap.cn, R.mipmap.dk, R.mipmap.fin, R.mipmap.fr, R.mipmap.sri, R.mipmap.ke};
    String name[] = {"India", "Brazil", "Canada", "Australia", "China", "Denmark", "Finland", "France", "Sri-Lanka", "Kenya"};
    String capital[] = {"New Delhi", "Brasília", "Ottawa", "Canberra", "Beijing", "Copenhagen", "Helsinki", "Paris", "Sri Jayawardenepura Kotte", "Nairobi"};
    ArrayList<GetterSetter> al = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        for (int i = 0; i < img.length; i++) {
            GetterSetter gs = new GetterSetter(img[i], name[i], capital[i]);
            al.add(gs);
        }

        MyAdapter adapter = new MyAdapter(MainActivity.this, al);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                {
                    Toast.makeText(MainActivity.this, "Country: " + name[position] + " and Capital: " + capital[position], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
