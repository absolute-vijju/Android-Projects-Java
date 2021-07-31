package com.example.mydoctorapp;

import com.google.gson.annotations.SerializedName;
import com.wang.avi.indicators.BallScaleRippleIndicator;

public class GetterSetterApprovedAppointment {

    @SerializedName("doc_fname")
    String doc_fname;
    @SerializedName("doc_lname")
    String doc_lname;
    @SerializedName("time")
    String time;
    @SerializedName("date")
    String date;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
