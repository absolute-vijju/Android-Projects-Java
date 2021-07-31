package com.example.mydoctorapp;

import com.example.mydoctorapp.Doctor.DoctorUser;
import com.example.mydoctorapp.Doctor.GetterSetterDocDetailsForDashboard;
import com.example.mydoctorapp.Doctor.GetterSetterDocTime;
import com.example.mydoctorapp.Patient.PatientUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("doctor_registration.php")
    Call<DoctorUser> addData(@Field("doc_fname") String doc_fname
            , @Field("doc_lname") String doc_lname
            , @Field("doc_gender") String doc_gender
            , @Field("doc_cliaddress") String doc_cliaddress
            , @Field("doc_phone") String doc_phone
            , @Field("doc_dob") String doc_dob
            , @Field("doc_speciality") String doc_speciality
            , @Field("doc_degree") String doc_degree
            , @Field("doc_username") String doc_username
            , @Field("doc_passwd") String doc_passwd);

    @FormUrlEncoded
    @POST("patient_registration.php")
    Call<PatientUser> addPatient(@Field("pat_fname") String pat_fname
            , @Field("pat_lname") String pat_lname
            , @Field("pat_gender") String pat_gender
            , @Field("pat_phone") String pat_phone
            , @Field("pat_dob") String pat_dob
            , @Field("pat_username") String pat_username
            , @Field("pat_passwd") String pat_passwd);

    @GET("login_doctor.php")
    Call<List<GetterSetterDocDetailsForDashboard>> getDocLoginDetail(@Query("doc_username") String doc_login_username
            , @Query("doc_passwd") String doc_login_passwd);

    @GET("login_patient.php")
    Call<List<GetterSetterPatient>> getPatLoginDetail(@Query("pat_username") String pat_login_username
            , @Query("pat_passwd") String pat_login_passwd);

    @GET("get_doctors.php")
    Call<List<GetterSetterDoctor>> getDoctors();

    @FormUrlEncoded
    @POST("insert_doctime.php")
    Call<GetterSetterDocTime> addDocTime(@Field("doc_id") String doc_id
            , @Field("monday") String monday
            , @Field("tuesday") String tuesday
            , @Field("wednesday") String wednesday
            , @Field("thursday") String thursday
            , @Field("friday") String friday
            , @Field("saturday") String saturday);

    @GET("get_nesteddoctors.php")
    Call<List<GetterSetterNestedDoctor>> getNestedDoctor(@Query("doc_id") String id);

    @FormUrlEncoded
    @POST("book_appointment.php")
    Call<GetterSetterAppointment> addAppointment(@Field("doc_id") String doc_id
            , @Field("pat_id") String pat_id
            , @Field("time") String time
            , @Field("date") String date
            , @Field("status") String status);

    @GET("get_pending_appointment.php")
    Call<List<GetterSetterPendingAppointment>> getPendingAppointment(@Query("doc_id") String doc_id);

    @FormUrlEncoded
    @POST("update_appointment.php")
    Call<GetterSetterUpdate> UpdateAppointment(@Field("app_id") String app_id
            , @Field("status") String status);


    @GET("get_approvedapp_forpatient.php")
    Call<List<GetterSetterApprovedAppointment>> getApprovedAppointment(@Query("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("insert_review.php")
    Call<GetterSetterReview> addReview(@Field("doc_id") String doc_id
            , @Field("pat_id") String pat_id
            , @Field("rating") float rating
            , @Field("review") String review);
}
