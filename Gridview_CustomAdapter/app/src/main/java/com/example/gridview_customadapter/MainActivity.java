package com.example.gridview_customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner sp;
    int img[] = {R.mipmap.in, R.mipmap.bd,R.mipmap.dr};
    String title[] = {"India", "Bangladesh","Dr. Strange"};
    ArrayList<GetterSetter> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = findViewById(R.id.sp);

        for (int i = 0; i < img.length; i++) {
            GetterSetter gs=new GetterSetter(img[i],title[i]);
            Log.d("test","Inside the loop");
            al.add(gs);
        }

        MyAdapter myAdapter=new MyAdapter(al,this);
        sp.setAdapter(myAdapter);

    }
}


