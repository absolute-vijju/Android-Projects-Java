package com.example.mydoctorapp.Doctor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mydoctorapp.ApiClient;
import com.example.mydoctorapp.ApiInterface;
import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetTimeForDoctor extends AppCompatActivity {

    private Button btnsave;
    private Spinner spmonday, sptuesday, spwednesday, spthursday, spfriday, spsaturday;
    private String val_mon, val_tue, val_wed, val_thu, val_fri, val_sat;
    ApiInterface apiInterface;
    private String doc_id;
    Toolbar toolbar;
    CheckConnection checkConnection;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settimefordoctor);

        /*if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/

        // For Checking Connection
        checkConnection = new CheckConnection(SetTimeForDoctor.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(SetTimeForDoctor.this)
                    .setTitle("Alert")
                    .setMessage("Internet not connected. Connect the internet and try again.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(SetTimeForDoctor.this, Dashboarddoctor.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        // End

        btnsave = findViewById(R.id.btnsave);
        spmonday = findViewById(R.id.spmonday);
        sptuesday = findViewById(R.id.sptuesday);
        spwednesday = findViewById(R.id.spwednesday);
        spthursday = findViewById(R.id.spthursday);
        spfriday = findViewById(R.id.spfriday);
        spsaturday = findViewById(R.id.spsaturday);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Available Timing");

        doc_id = getIntent().getStringExtra("doc_id");

        spmonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((spmonday.getItemAtPosition(position).toString().equals("Pick Your Time"))) {
                    val_mon = "Not Available";
                    Log.d("mydata", "Monday: " + val_mon);
                } else {
                    val_mon = spmonday.getItemAtPosition(position).toString();
                    Log.d("mydata", "Monday: " + val_mon);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sptuesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((sptuesday.getItemAtPosition(position).toString().equals("Pick Your Time"))) {
                    val_tue = "Not Available";
                    Log.d("mydata", "Tuesday: " + val_tue);
                } else {
                    val_tue = sptuesday.getItemAtPosition(position).toString();
                    Log.d("mydata", "Tuesday: " + val_tue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spwednesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((spwednesday.getItemAtPosition(position).toString().equals("Pick Your Time"))) {
                    val_wed = "Not Available";
                    Log.d("mydata", "Wednesday: " + val_wed);
                } else {
                    val_wed = spwednesday.getItemAtPosition(position).toString();
                    Log.d("mydata", "Wednesday: " + val_wed);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spthursday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((spthursday.getItemAtPosition(position).toString().equals("Pick Your Time"))) {
                    val_thu = "Not Available";
                    Log.d("mydata", "Thursday: " + val_thu);
                } else {
                    val_thu = spthursday.getItemAtPosition(position).toString();
                    Log.d("mydata", "Thursday: " + val_thu);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spfriday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((spfriday.getItemAtPosition(position).toString().equals("Pick Your Time"))) {
                    val_fri = "Not Available";
                    Log.d("mydata", "Friday: " + val_fri);
                } else {
                    val_fri = spfriday.getItemAtPosition(position).toString();
                    Log.d("mydata", "Friday: " + val_fri);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spsaturday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((spsaturday.getItemAtPosition(position).toString().equals("Pick Your Time"))) {
                    val_sat = "Not Available";
                    Log.d("mydata", "Saturday: " + val_sat);
                } else {
                    val_sat = spsaturday.getItemAtPosition(position).toString();
                    Log.d("mydata", "Saturday: " + val_sat);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(SetTimeForDoctor.this, "Saving Your Schedule", "Please wait. . .", false, false);
                apiInterface = ApiClient.getAliClient().create(ApiInterface.class);

                Call<GetterSetterDocTime> call = apiInterface.addDocTime(doc_id, val_mon, val_tue, val_wed, val_thu, val_fri, val_sat);

                call.enqueue(new Callback<GetterSetterDocTime>() {
                    @Override
                    public void onResponse(Call<GetterSetterDocTime> call, Response<GetterSetterDocTime> response) {
                        GetterSetterDocTime getterSetterDocTime = response.body();
                        progressDialog.dismiss();
                        Toast.makeText(SetTimeForDoctor.this, "Your Schedule Successfully Saved.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SetTimeForDoctor.this, Dashboarddoctor.class);
                        startActivity(i);
                        finish();
                        spmonday.setSelection(0);
                        sptuesday.setSelection(0);
                        spwednesday.setSelection(0);
                        spthursday.setSelection(0);
                        spfriday.setSelection(0);
                        spsaturday.setSelection(0);
                    }

                    @Override
                    public void onFailure(Call<GetterSetterDocTime> call, Throwable t) {

                    }
                });
            }
        });

    }
}
