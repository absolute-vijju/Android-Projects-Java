package com.example.swipe_delete;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderData {
    @SerializedName("slidImg")
    List<NestedSliderData> nestedSliderData;
    @SerializedName("path")
    private String path;

    public List<NestedSliderData> getNestedSliderData() {
        return nestedSliderData;
    }

    public String getPath() {
        return path;
    }
}
