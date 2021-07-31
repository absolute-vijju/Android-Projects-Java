package com.example.full_project;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginSharedPreferenceFile {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String user_id, email, first_name, last_name, token, profile_pic, login;

    public LoginSharedPreferenceFile(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("LOGIN_PREF_FILE", Context.MODE_PRIVATE);
    }

    public void writeData(String id, String _email, String fname, String lname, String _token, String pro_pic, boolean status) {
        editor = sp.edit();
        editor.putString("user_id", id);
        editor.putString("email", _email);
        editor.putString("first_name", fname);
        editor.putString("last_name", lname);
        editor.putString("token", _token);
        editor.putString("profile_pic", pro_pic);
        editor.putBoolean("login", status);
        editor.commit();
    }

    public String[] getData() {
        String data[] = {
                sp.getString("user_id", "user_id")
                , sp.getString("email", "email")
                , sp.getString("first_name", "firstname")
                , sp.getString("last_name", "lastname")
                , sp.getString("token", "token")
                , sp.getString("profile_pic", "pro_pic")};

        return data;
    }

    public boolean getStatus() {
        boolean status = sp.getBoolean("login", false);
        return status;
    }

    public void clearData() {
        editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}

