package com.example.first_project;

import com.example.first_project.Login.Login;
import com.example.first_project.Registration.Registration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("create-account")
    Call<Registration> insertData(@Field("email") String email
            , @Field("first_name") String first_name
            , @Field("last_name") String last_name
            , @Field("password") String password
            , @Field("profile_pic") String profile_pic);

    @FormUrlEncoded
    @POST("login")
    Call<Login> loginUser(@Field("email") String email
            , @Field("password") String password);


}
