package com.example.first_project.Login;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    String message;
    /*@SerializedName("data")
    LoginData loginData;*/


    public String getMessage() {
        return message;
    }


}
