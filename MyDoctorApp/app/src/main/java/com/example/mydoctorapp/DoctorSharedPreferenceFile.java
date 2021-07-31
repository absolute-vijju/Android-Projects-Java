package com.example.mydoctorapp;

import android.content.Context;
import android.content.SharedPreferences;

public class DoctorSharedPreferenceFile {
    Context context;
    public static String id = "DEFAULT_ID";
    public static String fname = "DEFAULT_FIRSTNAME";
    public static String lname = "DEFAULT_LASTNAME";
    public static String LOGIN_STATUS = "false";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public DoctorSharedPreferenceFile(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("doc_sharedpref_file", Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(Boolean status, String ID, String firstname, String lastname) {
        editor = sp.edit();
        editor.putBoolean(LOGIN_STATUS, status);
        editor.putString(id, ID);
        editor.putString(fname, firstname);
        editor.putString(lname, lastname);
        editor.commit();
    }

    public String getId() {
        String get_id;
        get_id = sp.getString(id, "DEFAULT_ID");
        return get_id;
    }

    public String getFirstname() {
        String f_name;
        f_name = sp.getString(fname, "DEFAULT_FIRSTNAME");
        return f_name;
    }

    public String getLastname() {
        String l_name;
        l_name = sp.getString(lname, "DEFAULT_LASTNAME");
        return l_name;
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
