package com.example.mydoctorapp.Doctor;

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
import com.example.mydoctorapp.DoctorSharedPreferenceFile;
import com.example.mydoctorapp.GetPendingAppointmentAdapter;
import com.example.mydoctorapp.GetterSetterPendingAppointment;
import com.example.mydoctorapp.Patient.SpecificDoctor;
import com.example.mydoctorapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingAppointment extends AppCompatActivity {

    private RecyclerView rev;
    String doc_id, fullname;
    ApiInterface apiInterface;
    List<GetterSetterPendingAppointment> pendingAppointments;
    DoctorSharedPreferenceFile dsp;
    Toolbar toolbar;
    CheckConnection checkConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingappointment);

        dsp = new DoctorSharedPreferenceFile(PendingAppointment.this);

        // For Checking Connection
        checkConnection = new CheckConnection(PendingAppointment.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(PendingAppointment.this)
                    .setTitle("Alert")
                    .setMessage("Internet not connected. Connect the internet and try again.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(PendingAppointment.this, Dashboarddoctor.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        // End

        doc_id = dsp.getId();

        rev = findViewById(R.id.penrev);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Pending Appointments");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PendingAppointment.this);
        rev.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
        Call<List<GetterSetterPendingAppointment>> call = apiInterface.getPendingAppointment(doc_id);

        call.enqueue(new Callback<List<GetterSetterPendingAppointment>>() {
            @Override
            public void onResponse(Call<List<GetterSetterPendingAppointment>> call, Response<List<GetterSetterPendingAppointment>> response) {
                pendingAppointments = response.body();

                GetPendingAppointmentAdapter adapter = new GetPendingAppointmentAdapter(PendingAppointment.this, pendingAppointments, doc_id, fullname);
                rev.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetterSetterPendingAppointment>> call, Throwable t) {

            }
        });

    }
}
