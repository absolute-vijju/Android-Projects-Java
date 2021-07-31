package com.example.searchview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass> {
    Context context;
    ArrayList<GetterSetter> al;
    GetterSetter gs;

    public MyAdapter(Context context, ArrayList<GetterSetter> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowfile, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass myClass, final int i) {
        gs = al.get(i);
        myClass.tv.setText(gs.getNames());
        myClass.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= al.get(i).getNames();
                Toast.makeText(context, "" + name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {
        TextView tv;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tvname);
        }
    }

    public void setFilter(ArrayList<GetterSetter> newAl) {
        al = new ArrayList<>();
        al.addAll(newAl);
        notifyDataSetChanged();
    }
}
