package com.example.mydoctorapp.Patient;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import com.example.mydoctorapp.Doctor.DoctorRegistration;
import com.example.mydoctorapp.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientRegistration extends AppCompatActivity {

    Button btnpdob, pbtnsave, regswitchdoctor;
    EditText petfname, petlname, petphone, petusername, petpasswd;
    RadioGroup prg;
    RadioButton prbmale, prbfemale, prbother;
    String pemailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    Calendar pcalendar = Calendar.getInstance();
    String pat_fname, pat_lname, pat_gender, pat_phone, pat_dob, pat_username, pat_passwd;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientregistration);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        btnpdob = findViewById(R.id.btnpdob);
        pbtnsave = findViewById(R.id.pbtnsave);
        petfname = findViewById(R.id.etpfname);
        petlname = findViewById(R.id.etplname);
        petphone = findViewById(R.id.etpphone);
        petusername = findViewById(R.id.etpusername);
        petpasswd = findViewById(R.id.etppassword);
        prg = findViewById(R.id.prg);
        prbmale = findViewById(R.id.prbmale);
        prbfemale = findViewById(R.id.prbfemale);
        prbother = findViewById(R.id.prbother);
        regswitchdoctor = findViewById(R.id.regswitchdoctor);

        regswitchdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientRegistration.this, DoctorRegistration.class);
                startActivity(i);
            }
        });

        prg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (prbmale.isChecked()) {
                    pat_gender = prbmale.getText().toString();
                } else if (prbfemale.isChecked()) {
                    pat_gender = prbfemale.getText().toString();
                } else if (prbother.isChecked()) {
                    pat_gender = prbother.getText().toString();
                } else {
                    pat_gender = "null";
                }
            }
        });

        btnpdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PatientRegistration.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int newMonth = 1 + month;
                        btnpdob.setText(dayOfMonth + "/" + newMonth + "/" + year);
                    }
                }
                        , pcalendar.get(Calendar.YEAR)
                        , pcalendar.get(Calendar.MONTH)
                        , pcalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        pbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (petusername.getText().toString().length() == 0
                        && petpasswd.getText().toString().length() == 0
                        && petfname.getText().toString().length() == 0
                        && petlname.getText().toString().length() == 0
                        && petphone.getText().toString().length() == 0) {
                    petusername.setError("Username Required");
                    petpasswd.setError("Password Required");
                    petfname.setError("Firstname Required");
                    petlname.setError("Lastname Required");
                    petphone.setError("Phone number Required");
                } else {
                    if (petusername.getText().toString().matches(pemailPattern)) {
                        progressDialog = ProgressDialog.show(PatientRegistration.this, "Creating Your Account", "Please Wait. . .", false, false);
                        if (petfname.getText().toString().length() == 0) {
                            pat_fname = "null";
                        } else {
                            pat_fname = petfname.getText().toString();
                        }
                        if (petlname.getText().toString().length() == 0) {
                            pat_lname = "null";
                        } else {
                            pat_lname = petlname.getText().toString();
                        }
                        if (petphone.getText().toString().length() == 0) {
                            pat_phone = "null";
                        } else {
                            pat_phone = petphone.getText().toString();
                        }
                        pat_dob = btnpdob.getText().toString();
                        if (petusername.getText().toString().length() == 0) {
                            pat_username = "null";
                        } else {
                            pat_username = petusername.getText().toString();
                        }
                        if (petpasswd.getText().toString().length() == 0) {
                            pat_passwd = "null";
                        } else {
                            pat_passwd = petpasswd.getText().toString();
                        }
                        Log.d("mydata", "Firstname: " + pat_fname);
                        Log.d("mydata", "Lastname: " + pat_lname);
                        Log.d("mydata", "Gender: " + pat_gender);
                        Log.d("mydata", "Phone: " + pat_phone);
                        Log.d("mydata", "Date of Birth: " + pat_dob);
                        Log.d("mydata", "Username: " + pat_username);
                        Log.d("mydata", "Passwod: " + pat_passwd);

                        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                        Call<PatientUser> call = apiInterface.addPatient(pat_fname, pat_lname, pat_gender, pat_phone, pat_dob, pat_username, pat_passwd);

                        call.enqueue(new Callback<PatientUser>() {
                            @Override
                            public void onResponse(Call<PatientUser> call, Response<PatientUser> response) {
                                PatientUser patientUser = response.body();
                                Toast.makeText(PatientRegistration.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent i = new Intent(PatientRegistration.this, PatientloginActivity.class);
                                startActivity(i);
                                finish();
                                petfname.getText().clear();
                                petlname.getText().clear();
                                prg.clearCheck();
                                petphone.getText().clear();
                                btnpdob.setText("Select Date");
                                petusername.getText().clear();
                                petpasswd.getText().clear();

                            }

                            @Override
                            public void onFailure(Call<PatientUser> call, Throwable t) {
                                Log.d("myerror", t.getMessage());
                                progressDialog.dismiss();
                            }
                        });
                    } else {
                        petusername.setError("Username Isn't Valid");
                        progressDialog.dismiss();
                    }
                }
            }
        });

    }
}
