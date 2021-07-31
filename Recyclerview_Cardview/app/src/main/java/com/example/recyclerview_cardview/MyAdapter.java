package com.example.recyclerview_cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        final GetterSetter gs = al.get(i);
        myClass.img.setImageResource(gs.getImg());
        myClass.tv.setText(gs.getName());

        myClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + gs.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    class MyClass extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tv;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imview);
            tv = itemView.findViewById(R.id.tv);

        }
    }
}
