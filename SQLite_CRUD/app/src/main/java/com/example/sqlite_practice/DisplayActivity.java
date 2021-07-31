package com.example.sqlite_practice;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView rev;
    ArrayList<GetterSetter> al = new ArrayList<>();
    MyDatabase db;
    Cursor c;
    String id, fname, lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        rev = findViewById(R.id.rev);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DisplayActivity.this);
        rev.setLayoutManager(layoutManager);

        db = new MyDatabase(DisplayActivity.this);

        c = db.getData();

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    id = c.getString(0);
                    fname = c.getString(1);
                    lname = c.getString(2);

                    GetterSetter gs = new GetterSetter(id, fname, lname);
                    al.add(gs);
                } while (c.moveToNext());
            }
        } else {
            setContentView(R.layout.empty);
        }

        MyAdapter myAdapter = new MyAdapter(DisplayActivity.this, al);
        rev.setAdapter(myAdapter);

    }
}
