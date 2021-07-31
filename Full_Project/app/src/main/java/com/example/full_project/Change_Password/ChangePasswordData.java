package com.example.full_project.Change_Password;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordData {
    @SerializedName("user_id")
    private String userid;
    @SerializedName("old_password")
    private String old_passwd;
    @SerializedName("password")
    private String new_passwd;
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
