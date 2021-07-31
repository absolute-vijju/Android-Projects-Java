package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("img_upload.php")
    Call<GetterSetter> uploadImage(@Field("image_name") String title, @Field("image") String image);
}
