package com.example.mydoctorapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SliderSharedPreferenceFile {
    Context context;
    public static String LOGIN_STATUS = "false";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SliderSharedPreferenceFile(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("slider_file", Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(Boolean status) {
        editor = sp.edit();
        editor.putBoolean(LOGIN_STATUS, status);
        editor.commit();
    }

    public boolean getStatus() {
        boolean status;
        status = sp.getBoolean(LOGIN_STATUS, false);
        return status;
    }

    public boolean readStatus() {
        Boolean status;
        status = sp.getBoolean(LOGIN_STATUS, false);
        return status;
    }

    public void clearLogin() {
        editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
