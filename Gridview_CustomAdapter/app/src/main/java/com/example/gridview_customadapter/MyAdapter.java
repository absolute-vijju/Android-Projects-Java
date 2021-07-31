package com.example.gridview_customadapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<GetterSetter> al;
    Context context;

    public MyAdapter(ArrayList<GetterSetter> al, Context context) {
        this.al = al;
        this.context = context;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return al.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.rowlayout,null,false);

        ImageView imageView;
        TextView textView;

        imageView=view.findViewById(R.id.imview);
        textView=view.findViewById(R.id.tv);

        GetterSetter gs=al.get(position);

        imageView.setImageResource(gs.getImage());
        textView.setText(gs.getTitle());

        return view;
    }
}
