package com.example.mydoctorapp;

import com.google.gson.annotations.SerializedName;

public class GetterSetterDoctor {

    @SerializedName("doc_username")
    String doc_login_username;
    @SerializedName("doc_passwd")
    String doc_login_passwd;

    @SerializedName("doc_id")
    String doc_id;

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    @SerializedName("doc_fname")
    String doc_fname;
    @SerializedName("doc_lname")
    String doc_lname;
    @SerializedName("doc_speciality")
    String doc_speciality;
    @SerializedName("doc_degree")
    String doc_degree;

    public String getDoc_login_username() {
        return doc_login_username;
    }

    public void setDoc_login_username(String doc_login_username) {
        this.doc_login_username = doc_login_username;
    }

    public String getDoc_login_passwd() {
        return doc_login_passwd;
    }

    public void setDoc_login_passwd(String doc_login_passwd) {
        this.doc_login_passwd = doc_login_passwd;
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

    public String getDoc_speciality() {
        return doc_speciality;
    }

    public void setDoc_speciality(String doc_speciality) {
        this.doc_speciality = doc_speciality;
    }

    public String getDoc_degree() {
        return doc_degree;
    }

    public void setDoc_degree(String doc_degree) {
        this.doc_degree = doc_degree;
    }
}
