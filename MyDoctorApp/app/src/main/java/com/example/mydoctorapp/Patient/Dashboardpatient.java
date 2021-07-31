package com.example.mydoctorapp.Patient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.Doctor.Dashboarddoctor;
import com.example.mydoctorapp.Doctor.DoctorloginActivity;
import com.example.mydoctorapp.R;
import com.example.mydoctorapp.SharedPreferenceFile;

public class Dashboardpatient extends AppCompatActivity {

    private LinearLayout cv1, cv2;
    private TextView tvpatient;
    private String pat_id;
    String spid, spfname, splname;
    Boolean spstatus;
    String fullname;
    SharedPreferenceFile spf;
    CheckConnection checkConnection;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardpatient);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // For Checking Connection
        checkConnection = new CheckConnection(Dashboardpatient.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(Dashboardpatient.this)
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

        spf = new SharedPreferenceFile(Dashboardpatient.this);

        spid = spf.getId();
        spfname = spf.getFirstname();
        splname = spf.getLastname();
        spstatus = spf.getStatus();

        Log.d("mydata", "id: " + spid);
        Log.d("mydata", "firstname: " + spfname);
        Log.d("mydata", "lastname: " + splname);
        Log.d("mydata", "status: " + spstatus);

        cv1 = findViewById(R.id.pbtn1);
        cv2 = findViewById(R.id.pbtn2);
        tvpatient = findViewById(R.id.tvpname);
        fab = findViewById(R.id.pfab);

        fullname = spfname + " " + splname;
        pat_id = spid;

        tvpatient.setText(fullname);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spf.clearLogin();
                Intent i = new Intent(Dashboardpatient.this, PatientloginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboardpatient.this, DoctordetailsForPatient.class);
                i.putExtra("pat_id", pat_id);
                startActivity(i);
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboardpatient.this, ApprovedAppointment.class);
                i.putExtra("pat_id", pat_id);
                startActivity(i);
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            spf.clearLogin();
            Intent i = new Intent(Dashboardpatient.this, PatientloginActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(Dashboardpatient.this)
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
