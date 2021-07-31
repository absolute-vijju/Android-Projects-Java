package com.example.first_project.Registration;

import com.google.gson.annotations.SerializedName;

public class Registration {
    @SerializedName("email")
    String email;
    @SerializedName("first_name")
    String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("password")
    String password;
    @SerializedName("profile_pic")
    String profile_pic;
    String message;

    public String getMessage() {
        return message;
    }
}
