package com.example.swipe_delete;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass> {

    Context context;
    List<NestedSliderData> al;
    private String path;

    public MyAdapter(Context context, List<NestedSliderData> al, String path) {
        this.context = context;
        this.al = al;
        this.path = path;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rowfile, parent, false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {
        holder.tvid.setText(al.get(position).getId());
        Picasso.with(context).load(path + al.get(position).getImageurl()).into(holder.imview);
        holder.tvname.setText(al.get(position).getName());

    }

    public String getName(int position) {
        String name = al.get(position).getName();
        return name;
    }

    public void removeItem(int position) {
        al.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, al.size());
    }

    public void restoreItem(NestedSliderData model, int position) {
        al.add(position, model);
        // notify item added by position
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        ImageView imview;
        TextView tvid, tvname;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            imview = itemView.findViewById(R.id.imview);
            tvid = itemView.findViewById(R.id.tvid);
            tvname = itemView.findViewById(R.id.tvname);
        }
    }
}
