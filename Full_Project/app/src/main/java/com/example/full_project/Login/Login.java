package com.example.full_project.Login;

import com.google.gson.annotations.SerializedName;

public class Login {
    private String email;
    private String password;
    private boolean success;
    private String message;
    @SerializedName("data")
    private LoginData loginData;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public boolean isSuccess() {
        return success;
    }
}
