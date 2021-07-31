package com.example.shared_practical;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class MySharedPreferenceFile {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static String keyData = "Data";

    public MySharedPreferenceFile(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
    }

    public void addData(String data) {
        editor = sp.edit();
        editor.putString(keyData, data);
        editor.commit();
    }

    public HashMap getData() {
        HashMap hm = new HashMap();
        hm.put(keyData, sp.getString(keyData, "You must login with your username"));
        return hm;
    }

    public void clearData() {
        editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }
}
