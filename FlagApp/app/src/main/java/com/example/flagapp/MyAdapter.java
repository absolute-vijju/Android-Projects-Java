package com.example.flagapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.rowlayout, null, false);

        ImageView im;
        TextView name, cap;

        im = v.findViewById(R.id.imview);
        name = v.findViewById(R.id.fname);
        cap = v.findViewById(R.id.fcap);

        GetterSetter gs = al.get(position);

        im.setImageResource(gs.getImg());
        name.setText("Country :" + gs.getName());
        cap.setText("Capital :" + gs.getCapital());

        return v;
    }

    public void updateAdapter(ArrayList<GetterSetter> newAl) {
        al = newAl;
        al.addAll(newAl);
        notifyDataSetChanged();
    }

}
