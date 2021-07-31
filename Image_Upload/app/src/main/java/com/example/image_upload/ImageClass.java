package com.example.image_upload;

import com.google.gson.annotations.SerializedName;

public class ImageClass {
    @SerializedName("title")
    String Title;
    @SerializedName("image")
    String Image;
    @SerializedName("response")
    String Response;

    public String getResponse() {
        return Response;
    }
}
