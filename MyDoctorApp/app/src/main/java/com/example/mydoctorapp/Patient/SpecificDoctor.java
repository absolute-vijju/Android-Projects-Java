package com.example.mydoctorapp.Patient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.ApiClient;
import com.example.mydoctorapp.ApiInterface;
import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.GetNestedDoctorDetailForPatientDashboardAdapter;
import com.example.mydoctorapp.GetterSetterNestedDoctor;
import com.example.mydoctorapp.R;
import com.example.mydoctorapp.SharedPreferenceFile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecificDoctor extends AppCompatActivity {

    TextView tv;
    String doc_id, pat_id;
    RecyclerView rev;
    List<GetterSetterNestedDoctor> nestedDoctorList;
    ApiInterface apiInterface;
    SharedPreferenceFile spf;
    CheckConnection checkConnection;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificdoctor);

        // For Checking Connection
        checkConnection = new CheckConnection(SpecificDoctor.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(SpecificDoctor.this)
                    .setTitle("Alert")
                    .setMessage("Internet not connected. Connect the internet and try again.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(SpecificDoctor.this, DoctordetailsForPatient.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        // End

        spf = new SharedPreferenceFile(SpecificDoctor.this);

        rev = findViewById(R.id.nestedrev);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Specific Doctor");

        doc_id = getIntent().getStringExtra("doc_id");
        pat_id = getIntent().getStringExtra("pat_id");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SpecificDoctor.this);
        rev.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
        Call<List<GetterSetterNestedDoctor>> call = apiInterface.getNestedDoctor(doc_id);

        call.enqueue(new Callback<List<GetterSetterNestedDoctor>>() {
            @Override
            public void onResponse(Call<List<GetterSetterNestedDoctor>> call, Response<List<GetterSetterNestedDoctor>> response) {
                nestedDoctorList = response.body();
                /*Log.d("mydata", nestedDoctorList.get(0).getFname());
                Log.d("mydata", nestedDoctorList.get(0).getLname());*/
                GetNestedDoctorDetailForPatientDashboardAdapter adapter = new GetNestedDoctorDetailForPatientDashboardAdapter(SpecificDoctor.this, nestedDoctorList, doc_id, pat_id);
                rev.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetterSetterNestedDoctor>> call, Throwable t) {
                Log.d("mydata", "Error " + t.getMessage());

            }
        });
    }
}
