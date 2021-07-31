package com.example.full_project;

import com.example.full_project.Change_Password.ChangePasswordData;
import com.example.full_project.Forget_Password.ForgetPassword;
import com.example.full_project.Login.Login;
import com.example.full_project.Register.RegistrationData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("create-account")
    Call<RegistrationData> addUser(@Field("email") String email
            , @Field("first_name") String fname
            , @Field("last_name") String lname
            , @Field("password") String password
            , @Field("profile_pic") String profile_pic);

    @POST("login")
    Call<Login> loginUser(@Body Login login);

    @FormUrlEncoded
    @POST("forgot-password")
    Call<ForgetPassword> sendPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("change-password")
    Call<ChangePasswordData> changePassword(@Header("Authorization") String token
            , @Field("user_id") String user_id
            , @Field("old_password") String old_passwd
            , @Field("password") String new_passwd);

}
