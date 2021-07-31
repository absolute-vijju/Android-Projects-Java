package com.example.first_project;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceFile {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String user_id, email, firstname, lastname;
    String status;

    public SharedPreferenceFile(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("myproj_file", Context.MODE_PRIVATE);
    }

    public void writeData(String Id, String Email, String Firstname, String Lastname) {
        editor = sp.edit();
        editor.putString(user_id, Id);
        editor.putString(email, Email);
        editor.putString(firstname, Firstname);
        editor.putString(lastname, Lastname);
        editor.commit();
    }

    public void writeLoginStatus(boolean Status) {
        editor = sp.edit();
        editor.putBoolean(status, Status);
        editor.commit();

    }

    public boolean getStatus() {
        return sp.getBoolean(status, false);
    }

    public String getUser_id() {
        return sp.getString(user_id, "0");
    }

    public String getEmail() {
        return sp.getString(email, "Email");
    }

    public String getFirstname() {
        return sp.getString(firstname, "Firstname");
    }

    public String getLastname() {
        return sp.getString(lastname, "Lastname");
    }
}
