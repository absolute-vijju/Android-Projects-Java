package com.example.retrofit_insert;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("insert_data.php")
    Call<Contacts> addData(@Field("doc_username") String doc_username,
                           @Field("doc_passwd") String doc_passwd);
}
