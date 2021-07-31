package com.example.dummyapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ModelClass modelClass;
    List<FoodItems> foodItems;

    public MyAdapter(Context context, ModelClass modelClass) {
        this.context = context;
        this.modelClass = modelClass;
        this.foodItems = modelClass.getFoodItems();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("mydata", String.valueOf(position));
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                Log.d("mydata", "Case 0 Created");
                View view1 = LayoutInflater.from(context).inflate(R.layout.first_layout, parent, false);
                return new FirstViewHolder(view1);
            case 1:
                Log.d("mydata", "Case 1 Created");
                View view2 = LayoutInflater.from(context).inflate(R.layout.second_layout, parent, false);
                return new SecondViewHolder(view2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ((FirstViewHolder) holder).setFirst(modelClass.getTotal_cal(), modelClass.getRemaining_cal(), modelClass.getUsed_cal());
                Log.d("mydata", "Case 0 Value Set");
                break;
            case 1:
                int id = foodItems.get(position - 1).getId();
                String name = foodItems.get(position - 1).getName();
                int quantity = foodItems.get(position - 1).getQuantity();
                ((SecondViewHolder) holder).setSecond(id, name, quantity);
                Log.d("mydata", "Case 1 Value Set" + position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return foodItems.size() + 1;
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCal, tvRemainingCal, tvUsedCal;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRemainingCal = itemView.findViewById(R.id.tvRemainingCal);
            tvTotalCal = itemView.findViewById(R.id.tvTotalCal);
            tvUsedCal = itemView.findViewById(R.id.tvUsedCal);

        }

        public void setFirst(int totalCal, int reminingCal, int usedCal) {
            tvUsedCal.setText(String.valueOf(usedCal));
            tvRemainingCal.setText(String.valueOf(reminingCal));
            tvTotalCal.setText(String.valueOf(totalCal));
        }
    }

    public class SecondViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvName, tvQuantity;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }

        public void setSecond(int id, String name, int quantity) {
            tvId.setText(String.valueOf(id));
            tvName.setText(name);
            tvQuantity.setText(String.valueOf(quantity));
        }
    }
}
