package com.example.retrofit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass> {

    List<Contact> contacts;

    public MyAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowfile, viewGroup, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass myClass, int i) {
        myClass.tv1.setText(contacts.get(i).getUsername());
        myClass.tv2.setText(contacts.get(i).getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {
        TextView tv1, tv2;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tvname);
            tv2 = itemView.findViewById(R.id.tvnumber);

        }
    }
}
