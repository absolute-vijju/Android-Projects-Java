package com.example.fragment_recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass> {
    Context context;
    ArrayList<GetterSetter> al;

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
    public void onBindViewHolder(@NonNull MyClass myClass, int i) {
        GetterSetter gs = al.get(i);
        myClass.imageView.setImageResource(gs.getImg());
        myClass.textView.setText(gs.getName());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imvflag);
            textView = itemView.findViewById(R.id.tvname);
        }
    }
}
