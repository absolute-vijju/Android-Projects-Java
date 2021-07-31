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
import com.example.mydoctorapp.GetDoctorDetailForPatientDashboardAdapter;
import com.example.mydoctorapp.GetterSetterDoctor;
import com.example.mydoctorapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctordetailsForPatient extends AppCompatActivity {

    RecyclerView rev;
    List<GetterSetterDoctor> doctorList;
    ApiInterface apiInterface;
    private String pat_id;
    Toolbar toolbar;
    CheckConnection checkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetails);

        rev = findViewById(R.id.rev);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Doctor List");

        pat_id = getIntent().getStringExtra("pat_id");

        // For Checking Connection
        checkConnection = new CheckConnection(DoctordetailsForPatient.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(DoctordetailsForPatient.this)
                    .setTitle("Alert")
                    .setMessage("Internet not connected. Connect the internet and try again.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(DoctordetailsForPatient.this, Dashboardpatient.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        // End

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DoctordetailsForPatient.this);
        rev.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);

        Call<List<GetterSetterDoctor>> call = apiInterface.getDoctors();

        call.enqueue(new Callback<List<GetterSetterDoctor>>() {
            @Override
            public void onResponse(Call<List<GetterSetterDoctor>> call, Response<List<GetterSetterDoctor>> response) {
                doctorList = response.body();

                GetDoctorDetailForPatientDashboardAdapter adapter = new GetDoctorDetailForPatientDashboardAdapter(DoctordetailsForPatient.this, doctorList, pat_id);
                rev.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetterSetterDoctor>> call, Throwable t) {

            }
        });
    }
}
