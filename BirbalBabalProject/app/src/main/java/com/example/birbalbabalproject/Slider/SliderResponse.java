package com.example.birbalbabalproject.Slider;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderResponse {
    @SerializedName("slidImg")
    private List<ResponseArray> responseArray;
    @SerializedName("path")
    private String path;

    public List<ResponseArray> getResponseArray() {
        return responseArray;
    }

    public String getPath() {
        return path;
    }
}
