package com.example.custom_adapter_with_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<GetterSetter> al;

    public MyAdapter(Context context, ArrayList<GetterSetter> al) {
        this.context = context;
        this.al = al;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.rowlayout, null, false);

        ImageView im;
        TextView tv;

        im = v.findViewById(R.id.imview);
        tv = v.findViewById(R.id.tvdata);

        GetterSetter gl = al.get(position);

        im.setImageResource(gl.getImg());
        tv.setText(gl.getTitle());

        return v;
    }
}
