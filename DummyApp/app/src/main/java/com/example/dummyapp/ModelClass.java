package com.example.dummyapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class ModelClass {
    private int total_cal;
    private int used_cal;
    private int remaining_cal;
    @SerializedName("diary_items")
    List<FoodItems> foodItems;

    public int getTotal_cal() {
        return total_cal;
    }

    public int getUsed_cal() {
        return used_cal;
    }

    public int getRemaining_cal() {
        return remaining_cal;
    }

    public List<FoodItems> getFoodItems() {
        return foodItems;
    }
}
