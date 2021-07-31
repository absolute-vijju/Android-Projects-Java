package com.example.birbalbabalproject.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    private String name, mobile, email, password, comp_name, location, cntry_code, comp_address, city, state, pincode, locality, country, web_url, gst_in;
    @SerializedName("otp")
    private String otp;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public RegisterResponse(String name, String mobile, String email, String password, String comp_name, String location, String cntry_code, String comp_address, String city, String state, String pincode, String locality, String country, String web_url, String gst_in) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.comp_name = comp_name;
        this.location = location;
        this.cntry_code = cntry_code;
        this.comp_address = comp_address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.locality = locality;
        this.country = country;
        this.web_url = web_url;
        this.gst_in = gst_in;
    }

    public String getOtp() {
        return otp;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
