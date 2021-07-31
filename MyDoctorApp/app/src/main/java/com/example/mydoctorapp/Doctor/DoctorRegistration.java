package com.example.mydoctorapp.Doctor;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mydoctorapp.ApiClient;
import com.example.mydoctorapp.ApiInterface;
import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.Patient.PatientRegistration;
import com.example.mydoctorapp.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorRegistration extends AppCompatActivity {

    Spinner dspspeciality, dspdegree;
    RadioGroup drg;
    RadioButton drbmale, drbfemale, drbother;
    EditText detfname, detlname, detusername, detpassword, etdphone, etcliaddress;
    Button dbtnsave, dbtndob, regswitchpatient;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    Calendar dcalendar = Calendar.getInstance();
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    CheckConnection checkConnection;

    String doc_fname, doc_lname, doc_gender, doc_cliaddress, doc_phone, doc_dob, doc_speciality, doc_degree, doc_username, doc_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorregistration);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        checkConnection = new CheckConnection(DoctorRegistration.this);

        if (!checkConnection.getConnection()) {
            new AlertDialog.Builder(DoctorRegistration.this)
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


        detfname = findViewById(R.id.etdfname);
        detlname = findViewById(R.id.etdlname);
        drg = findViewById(R.id.drg);
        drbmale = findViewById(R.id.drbmale);
        drbfemale = findViewById(R.id.drbfemale);
        drbother = findViewById(R.id.drbother);
        etcliaddress = findViewById(R.id.etcliaddress);
        etdphone = findViewById(R.id.etdphone);
        dbtndob = findViewById(R.id.btnddob);
        dspspeciality = findViewById(R.id.dspspeciality);
        dspdegree = findViewById(R.id.dspdegree);
        detusername = findViewById(R.id.detusername);
        detpassword = findViewById(R.id.detpassword);
        dbtnsave = findViewById(R.id.dbtnsave);
        regswitchpatient = findViewById(R.id.regswitchpatient);

        regswitchpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorRegistration.this, PatientRegistration.class);
                startActivity(i);
            }
        });

        drg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (drbmale.isChecked()) {
                    doc_gender = drbmale.getText().toString();
                } else if (drbfemale.isChecked()) {
                    doc_gender = drbfemale.getText().toString();
                } else if (drbother.isChecked()) {
                    doc_gender = drbother.getText().toString();
                } else {
                    doc_gender = "null";
                }
            }
        });

        dbtndob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DoctorRegistration.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int newMonth = 1 + month;
                        dbtndob.setText(dayOfMonth + "/" + newMonth + "/" + year);
                    }
                }
                        , dcalendar.get(Calendar.YEAR)
                        , dcalendar.get(Calendar.MONTH)
                        , dcalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
        dspspeciality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doc_speciality = dspspeciality.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dspdegree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doc_degree = dspdegree.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detusername.getText().toString().length() == 0
                        && detpassword.getText().toString().length() == 0
                        && detfname.getText().toString().length() == 0
                        && detusername.getText().toString().length() == 0
                        && detlname.getText().toString().length() == 0
                        && etcliaddress.getText().toString().length() == 0
                        && etdphone.getText().toString().length() == 0) {
                    detusername.setError("Username Required");
                    detpassword.setError("Password Required");
                    detfname.setError("Firstname Required");
                    detlname.setError("Lastname Required");
                    etcliaddress.setError("Clinic Address Required");
                    etdphone.setError("Phone number Required");
                    detlname.setError("");
                } else {
                    if (detusername.getText().toString().matches(emailPattern)) {
                        progressDialog = ProgressDialog.show(DoctorRegistration.this, "Creating Your Account", "Please Wait. . .", false, false);
                        if (detfname.getText().toString().length() == 0) {
                            doc_fname = "null";
                        } else {
                            doc_fname = detfname.getText().toString();
                        }
                        if (detlname.getText().toString().length() == 0) {
                            doc_lname = "null";
                        } else {
                            doc_lname = detlname.getText().toString();
                        }
                        if (etcliaddress.getText().toString().length() == 0) {
                            doc_cliaddress = "null";
                        } else {
                            doc_cliaddress = etcliaddress.getText().toString();
                        }
                        if (etdphone.getText().toString().length() == 0) {
                            doc_phone = "null";
                        } else {
                            doc_phone = etdphone.getText().toString();
                        }
                        doc_dob = dbtndob.getText().toString();
                        if (detusername.getText().toString().length() == 0) {
                            doc_username = "null";
                        } else {
                            doc_username = detusername.getText().toString();
                        }
                        if (detpassword.getText().toString().length() == 0) {
                            doc_passwd = "null";
                        } else {
                            doc_passwd = detpassword.getText().toString();
                        }

                        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                        Call<DoctorUser> call = apiInterface.addData(doc_fname, doc_lname, doc_gender, doc_cliaddress, doc_phone, doc_dob, doc_speciality, doc_degree, doc_username, doc_passwd);
                        call.enqueue(new Callback<DoctorUser>() {
                            @Override
                            public void onResponse(Call<DoctorUser> call, Response<DoctorUser> response) {
                                DoctorUser users = response.body();
                                progressDialog.dismiss();
                                Intent i = new Intent(DoctorRegistration.this, DoctorloginActivity.class);
                                Toast.makeText(DoctorRegistration.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                finish();
                                detfname.getText().clear();
                                detlname.getText().clear();
                                etcliaddress.getText().clear();
                                etdphone.getText().clear();
                                detusername.getText().clear();
                                detpassword.getText().clear();
                                dbtndob.setText("Select Date");
                                drg.clearCheck();
                                dspspeciality.setSelection(0);
                                dspdegree.setSelection(0);

                            }

                            @Override
                            public void onFailure(Call<DoctorUser> call, Throwable t) {
                                progressDialog.dismiss();
                                Log.d("myerror", t.getMessage());
                            }
                        });
                    } else {
                        detusername.setError("Username Isn't Valid");
                        progressDialog.dismiss();
                    }
                }
            }
        });

    }
}
