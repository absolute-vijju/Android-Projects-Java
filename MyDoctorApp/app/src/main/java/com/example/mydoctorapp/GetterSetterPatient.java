package com.example.mydoctorapp;

import com.google.gson.annotations.SerializedName;

public class GetterSetterPatient {

    @SerializedName("pat_id")
    String pat_pat_id;
    @SerializedName("pat_fname")
    String pat_login_fname;
    @SerializedName("pat_lname")
    String pat_login_lname;
    @SerializedName("pat_username")
    String pat_login_username;
    @SerializedName("pat_passwd")
    String pat_login_passwd;

    public String getPat_pat_id() {
        return pat_pat_id;
    }

    public void setPat_pat_id(String pat_pat_id) {
        this.pat_pat_id = pat_pat_id;
    }

    public String getPat_login_fname() {
        return pat_login_fname;
    }

    public void setPat_login_fname(String pat_login_fname) {
        this.pat_login_fname = pat_login_fname;
    }

    public String getPat_login_lname() {
        return pat_login_lname;
    }

    public void setPat_login_lname(String pat_login_lname) {
        this.pat_login_lname = pat_login_lname;
    }

    public String getPat_login_username() {
        return pat_login_username;
    }

    public void setPat_login_username(String pat_login_username) {
        this.pat_login_username = pat_login_username;
    }

    public String getPat_login_passwd() {
        return pat_login_passwd;
    }

    public void setPat_login_passwd(String pat_login_passwd) {
        this.pat_login_passwd = pat_login_passwd;
    }
}
