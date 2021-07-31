package com.example.birbalbabalproject.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private String username;
    private String password;
    private boolean success;
    private String id;
    private String user_type;
    @SerializedName("msg")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getId() {
        return id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getMessage() {
        return message;
    }
}
