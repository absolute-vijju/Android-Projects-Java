package com.example.retrofit_specific;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("get_specific_data.php")
    Call<List<Contacts>> getSpecificData(@Query("id") String id);
}
