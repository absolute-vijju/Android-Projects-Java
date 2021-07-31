package com.example.searchview;

import android.net.MailTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rev;
    String names[] = {"Hardik", "Vijay", "Vraj", "Karan", "Mehul", "Sumit", "Rohan", "Dharmik", "Nirav", "Tirth", "Ajay", "Prince", "Yash", "Thor", "Iron Man"};
    ArrayList<GetterSetter> al = new ArrayList<>();
    GetterSetter gs;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rev = findViewById(R.id.rev);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rev.setLayoutManager(layoutManager);

        for (int i = 0; i < names.length; i++) {
            GetterSetter gs = new GetterSetter(names[i]);
            al.add(gs);
        }

        myAdapter = new MyAdapter(MainActivity.this, al);
        rev.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);

        MenuItem menuItem = menu.findItem(R.id.op_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                String newText = s.toLowerCase();
                ArrayList<GetterSetter> newAl = new ArrayList<>();
                for (GetterSetter gs : al) {
                    String name = gs.getNames().toLowerCase();
                    if (name.contains(newText)) {
                        newAl.add(gs);
                    }
                }

                myAdapter.setFilter(newAl);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
