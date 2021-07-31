package com.example.mydoctorapp;

import com.google.gson.annotations.SerializedName;

public class GetterSetterPendingAppointment {
    @SerializedName("app_id")
    String app_id;
    @SerializedName("pat_fname")
    String pat_fname;
    @SerializedName("pat_lname")
    String pat_lname;
    @SerializedName("time")
    String time;
    @SerializedName("date")
    String date;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getPat_fname() {
        return pat_fname;
    }

    public void setPat_fname(String pat_fname) {
        this.pat_fname = pat_fname;
    }

    public String getPat_lname() {
        return pat_lname;
    }

    public void setPat_lname(String pat_lname) {
        this.pat_lname = pat_lname;
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
