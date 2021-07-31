package com.example.dummyapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APiInterface {
    @GET("4da6a2f8-d8b4-11e9-9ec2-93e5d50a6eec")
    Call<ModelClass> getData();
}
