package com.example.mydoctorapp.Patient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mydoctorapp.ApiClient;
import com.example.mydoctorapp.ApiInterface;
import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.GetApprovedAppointmentAdapter;
import com.example.mydoctorapp.GetterSetterApprovedAppointment;
import com.example.mydoctorapp.R;
import com.example.mydoctorapp.SharedPreferenceFile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedAppointment extends AppCompatActivity {

    RecyclerView rev;
    String pat_id;
    ApiInterface apiInterface;
    List<GetterSetterApprovedAppointment> approvedAppointments;
    SharedPreferenceFile spf;
    CheckConnection checkConnection;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvedappointment);

        spf = new SharedPreferenceFile(ApprovedAppointment.this);

        // For Checking Connection
        checkConnection = new CheckConnection(ApprovedAppointment.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(ApprovedAppointment.this)
                    .setTitle("Alert")
                    .setMessage("Internet not connected. Connect the internet and try again.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ApprovedAppointment.this, Dashboardpatient.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        // End

        rev = findViewById(R.id.appointmentapprovalrev);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Approved Appointments");

        pat_id = spf.getId();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ApprovedAppointment.this);
        rev.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
        Call<List<GetterSetterApprovedAppointment>> call = apiInterface.getApprovedAppointment(pat_id);

        call.enqueue(new Callback<List<GetterSetterApprovedAppointment>>() {
            @Override
            public void onResponse(Call<List<GetterSetterApprovedAppointment>> call, Response<List<GetterSetterApprovedAppointment>> response) {
                approvedAppointments = response.body();
                GetApprovedAppointmentAdapter adapter = new GetApprovedAppointmentAdapter(ApprovedAppointment.this, approvedAppointments);
                rev.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetterSetterApprovedAppointment>> call, Throwable t) {
                Log.d("mydata", t.getMessage());
            }
        });

    }
}
