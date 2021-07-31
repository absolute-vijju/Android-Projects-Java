package com.example.birbalbabalproject;

import com.example.birbalbabalproject.Login.LoginResponse;
import com.example.birbalbabalproject.Register.RegisterResponse;
import com.example.birbalbabalproject.Slider.SliderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("VendorReg")
    Call<RegisterResponse> addUser(@Field("name") String name
            , @Field("mobile") String mobile
            , @Field("email") String email
            , @Field("password") String password
            , @Field("comp_name") String comp_name
            , @Field("location") String location
            , @Field("cntry_code") String cntry_code
            , @Field("comp_address") String comp_address
            , @Field("city") String city
            , @Field("state") String state
            , @Field("pincode") String pincode
            , @Field("locality") String locality
            , @Field("country") String country
            , @Field("web_url") String web_url
            , @Field("gst_in") String gst_in);

    @FormUrlEncoded
    @POST("Login")
    Call<LoginResponse> loginUser(@Field("username") String username
            , @Field("password") String password);

    @POST("Slider")
    Call<SliderResponse> getSliders();
}
