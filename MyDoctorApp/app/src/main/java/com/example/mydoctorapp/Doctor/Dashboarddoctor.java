package com.example.mydoctorapp.Doctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.DoctorSharedPreferenceFile;
import com.example.mydoctorapp.Patient.Dashboardpatient;
import com.example.mydoctorapp.Patient.PatientloginActivity;
import com.example.mydoctorapp.R;
import com.example.mydoctorapp.SharedPreferenceFile;

public class Dashboarddoctor extends AppCompatActivity {

    TextView tvdname;
    LinearLayout lltime, llapp;
    String doc_id, fullname;
    String spid, spfname, splname;
    Boolean spstatus;
    DoctorSharedPreferenceFile dsp;
    CheckConnection checkConnection;
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboarddoctor);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        dsp = new DoctorSharedPreferenceFile(Dashboarddoctor.this);

        // For Checking Connection
        checkConnection = new CheckConnection(Dashboarddoctor.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(Dashboarddoctor.this)
                    .setTitle("Alert")
                    .setMessage("Internet not connected. Connect the internet and try again.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        // End

/*
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
*/

        tvdname = findViewById(R.id.tvdname);
        lltime = findViewById(R.id.btn2);
        llapp = findViewById(R.id.btn3);
        fab = findViewById(R.id.dfab);
        drawerLayout = findViewById(R.id.docdrawerlayout);
        navigationView = findViewById(R.id.docnav);

        spid = dsp.getId();
        spfname = dsp.getFirstname();
        splname = dsp.getLastname();
        spstatus = dsp.getStatus();

        Log.d("mydata", spid);
        Log.d("mydata", spfname);
        Log.d("mydata", splname);
        Log.d("mydata", String.valueOf(spstatus));

        doc_id = dsp.getId();
        fullname = spfname + " " + splname;

        tvdname.setText("Dr. " + fullname);

        lltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboarddoctor.this, SetTimeForDoctor.class);
                i.putExtra("doc_id", doc_id);
                startActivity(i);
            }
        });

        llapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboarddoctor.this, PendingAppointment.class);
                startActivity(i);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsp.clearLogin();
                Intent i = new Intent(Dashboarddoctor.this, DoctorloginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        /*int id = item.getItemId();
        if (id == R.id.logout) {
            dsp.clearLogin();
            Intent i = new Intent(Dashboarddoctor.this, DoctorloginActivity.class);
            startActivity(i);
            finish();
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(Dashboarddoctor.this)
                .setTitle("Alert")
                .setMessage("Are you sure want to close the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
