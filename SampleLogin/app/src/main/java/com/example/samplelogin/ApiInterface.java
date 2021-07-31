package com.example.samplelogin;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("create-account")
    Call<MyResponse> addUser(@Part MultipartBody.Part file
            , @Part("email") RequestBody email
            , @Part("first_name") RequestBody first_name
            , @Part("last_name") RequestBody last_name
            , @Part("password") RequestBody password);

}
