package com.example.retrofit_registration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("login_data_09_March.php")
    Call<List<Contacts>> getSpecificData(@Query("doc_username") String doc_username, @Query("doc_passwd") String doc_passwd);
}
