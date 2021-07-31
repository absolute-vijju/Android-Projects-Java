package com.example.mydoctorapp.Patient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctorapp.ApiClient;
import com.example.mydoctorapp.ApiInterface;
import com.example.mydoctorapp.CheckConnection;
import com.example.mydoctorapp.Doctor.DoctorloginActivity;
import com.example.mydoctorapp.GetterSetterPatient;
import com.example.mydoctorapp.R;
import com.example.mydoctorapp.SharedPreferenceFile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientloginActivity extends AppCompatActivity {

    Button pbtnlogin, switchdoctor;
    EditText petusername, petpasswd;
    String pat_username, pat_usernameNew, pat_passwd, pat_passwdNew;
    String pat_id, pat_firstname, pat_lastname, fullname;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    List<GetterSetterPatient> pat_login_details;
    SharedPreferenceFile spf;
    TextView ptvaccount;
    String mystring = new String("Sign Up");
    CheckConnection checkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientlogin);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // For Checking Connection
        checkConnection = new CheckConnection(PatientloginActivity.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(PatientloginActivity.this)
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

        spf = new SharedPreferenceFile(PatientloginActivity.this);

        if (spf.readStatus()) {
            Intent i = new Intent(PatientloginActivity.this, Dashboardpatient.class);
            startActivity(i);
            finish();
        }

        pbtnlogin = findViewById(R.id.pbtnlogin);
        petusername = findViewById(R.id.petusername);
        petpasswd = findViewById(R.id.petpasswd);
        ptvaccount = findViewById(R.id.mytv);
        switchdoctor = findViewById(R.id.switchdoctor);

        switchdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientloginActivity.this, DoctorloginActivity.class);
                startActivity(i);
                finish();
            }
        });

        //For Underline in Textview
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        ptvaccount.setText(content);
        // End

        pbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (petusername.getText().toString().length() == 0 && petpasswd.getText().toString().length() == 0) {
                    petusername.setError("Username Must Required");
                    petpasswd.setError("Password Must Required");
                } else {
                    if (petusername.getText().toString().matches(emailPattern)) {

                        progressDialog = ProgressDialog.show(PatientloginActivity.this, "Loading", "Please Wait . . .", false, false);

                        /*final Dialog d = new Dialog(PatientloginActivity.this);
                        d.setContentView(R.layout.progressfile);
                        d.setCancelable(false);
                        d.show();*/

                        pat_username = petusername.getText().toString();
                        pat_passwd = petpasswd.getText().toString();

                        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                        Call<List<GetterSetterPatient>> call = apiInterface.getPatLoginDetail(pat_username, pat_passwd);

                        call.enqueue(new Callback<List<GetterSetterPatient>>() {
                            @Override
                            public void onResponse(Call<List<GetterSetterPatient>> call, Response<List<GetterSetterPatient>> response) {
                                try {
                                    pat_login_details = response.body();
                                    pat_id = pat_login_details.get(0).getPat_pat_id();
                                    pat_firstname = pat_login_details.get(0).getPat_login_fname();
                                    pat_lastname = pat_login_details.get(0).getPat_login_lname();
                                    pat_usernameNew = pat_login_details.get(0).getPat_login_username();
                                    pat_passwdNew = pat_login_details.get(0).getPat_login_passwd();
                                    spf.writeLoginStatus(true, pat_id, pat_firstname, pat_lastname);
                                    fullname = (pat_firstname + " " + pat_lastname);
//                                  d.dismiss();
                                    progressDialog.dismiss();
                                    Toast.makeText(PatientloginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(PatientloginActivity.this, Dashboardpatient.class);
                                    i.putExtra("username", fullname);
                                    i.putExtra("pat_id", pat_id);
                                    startActivity(i);
                                    finish();
                                } catch (Exception e) {
//                                    d.dismiss();
                                    progressDialog.dismiss();
                                    Toast.makeText(PatientloginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    Log.d("mydata", "Error: " + e.getMessage());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<GetterSetterPatient>> call, Throwable t) {
                                progressDialog.dismiss();
                            }
                        });

                    } else {
                        petusername.setError("Username Isn't Valid");
                    }
                }
            }
        });

        ptvaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientloginActivity.this, PatientRegistration.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(PatientloginActivity.this)
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
