package com.example.first_project.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("email")
    private String email;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("token")
    private String token;

    public String getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getToken() {
        return token;
    }
}
