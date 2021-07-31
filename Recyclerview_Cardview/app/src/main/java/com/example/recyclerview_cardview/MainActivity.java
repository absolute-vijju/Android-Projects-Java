package com.example.recyclerview_cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rev;
    int img[] = {R.mipmap.dr, R.mipmap.emma};
    String name[] = {"Dr. Strange", "Emma Watson"};
    ArrayList<GetterSetter> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rev = findViewById(R.id.rev);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        rev.setLayoutManager(lm);

        for (int i = 0; i < img.length; i++) {
            GetterSetter gs = new GetterSetter(img[i], name[i]);
            al.add(gs);
        }

        MyAdapter my = new MyAdapter(this, al);
        rev.setAdapter(my);

    }
}

class GetterSetter {
    int img;
    String name;

    public GetterSetter(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
