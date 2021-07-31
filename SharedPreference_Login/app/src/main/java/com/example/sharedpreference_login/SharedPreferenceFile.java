package com.example.sharedpreference_login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPreferenceFile {

    Context context;
    public static String KEY_WRITE = "writelogin";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static String name = "TEST";


    public SharedPreferenceFile(Context context) {
        this.context = context;
        sp = (SharedPreferences) context.getSharedPreferences("mypref_file", Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        editor = sp.edit();
        editor.putBoolean(KEY_WRITE, status);
        editor.commit();
    }

    public void writeName(String LoginName) {
        editor = sp.edit();
        editor.putString(name, LoginName);
        editor.commit();
    }

    public String getName() {
        String value;
        value = sp.getString(name, "TEST");
        return value;
    }

    public boolean readLoginStatus() {
        boolean status;
        status = sp.getBoolean(KEY_WRITE, false);
        return status;
    }

    public void clearData() {
        editor = sp.edit();
        editor.clear();
        editor.commit();
        /*Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);*/
    }
}
