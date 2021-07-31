package com.example.mydoctorapp.Doctor;

import com.google.gson.annotations.SerializedName;

public class GetterSetterDocDetailsForDashboard {
    @SerializedName("doc_id")
    String doc_id;
    @SerializedName("doc_fname")
    String doc_fname;
    @SerializedName("doc_lname")
    String doc_lname;
    @SerializedName("doc_username")
    String doc_username;
    @SerializedName("doc_passwd")
    String doc_passwd;

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_fname() {
        return doc_fname;
    }

    public void setDoc_fname(String doc_fname) {
        this.doc_fname = doc_fname;
    }

    public String getDoc_lname() {
        return doc_lname;
    }

    public void setDoc_lname(String doc_lname) {
        this.doc_lname = doc_lname;
    }

    public String getDoc_username() {
        return doc_username;
    }

    public void setDoc_username(String doc_username) {
        this.doc_username = doc_username;
    }

    public String getDoc_passwd() {
        return doc_passwd;
    }

    public void setDoc_passwd(String doc_passwd) {
        this.doc_passwd = doc_passwd;
    }
}
