package com.example.mydoctorapp;

import com.google.gson.annotations.SerializedName;

public class GetterSetterNestedDoctor {
    @SerializedName("doc_id")
    String id;
    @SerializedName("doc_fname")
    String fname;
    @SerializedName("doc_lname")
    String lname;
    @SerializedName("doc_speciality")
    String speciality;
    @SerializedName("doc_degree")
    String degree;
    @SerializedName("doc_cliaddress")
    String cliaddress;
    @SerializedName("monday")
    String mon;
    @SerializedName("tuesday")
    String tue;
    @SerializedName("wednesday")
    String wed;
    @SerializedName("thursday")
    String thu;
    @SerializedName("friday")
    String fri;
    @SerializedName("saturday")
    String sat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCliaddress() {
        return cliaddress;
    }

    public void setCliaddress(String cliaddress) {
        this.cliaddress = cliaddress;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTue() {
        return tue;
    }

    public void setTue(String tue) {
        this.tue = tue;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }
}
