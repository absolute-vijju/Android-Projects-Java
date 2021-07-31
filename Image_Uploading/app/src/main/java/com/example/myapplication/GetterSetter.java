package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class GetterSetter {
    @SerializedName("image_name")
    private String Title;

    @SerializedName("image")
    private String Image;


    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
