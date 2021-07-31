package com.example.swipe_delete;

import com.google.gson.annotations.SerializedName;

public class NestedSliderData {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String imageurl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageurl() {
        return imageurl;
    }
}
