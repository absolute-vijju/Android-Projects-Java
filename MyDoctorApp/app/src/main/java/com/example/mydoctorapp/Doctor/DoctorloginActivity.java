package com.example.mydoctorapp.Doctor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.mydoctorapp.DoctorSharedPreferenceFile;
import com.example.mydoctorapp.GetterSetterDoctor;
import com.example.mydoctorapp.Patient.Dashboardpatient;
import com.example.mydoctorapp.Patient.PatientloginActivity;
import com.example.mydoctorapp.R;
import com.example.mydoctorapp.SharedPreferenceFile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorloginActivity extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    EditText dloginusername, dloginpasswd;
    Button btndlogin, switchpatient;
    String doc_username, doc_usernameNew, doc_passwd, doc_passwdNew, doc_id, doc_fname, doc_lname, doc_fullname;
    ProgressDialog progressDialog;
    DoctorSharedPreferenceFile dsp;
    SharedPreferenceFile psp;
    TextView dtvaccount;
    String mystring = new String("Sign Up");
    ApiInterface apiInterface;
    List<GetterSetterDocDetailsForDashboard> doc_login_details;
    CheckConnection checkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogin);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        checkConnection = new CheckConnection(DoctorloginActivity.this);
        if (checkConnection.getConnection()) {
        } else {
            new AlertDialog.Builder(DoctorloginActivity.this)
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

        dsp = new DoctorSharedPreferenceFile(DoctorloginActivity.this);
        psp = new SharedPreferenceFile(DoctorloginActivity.this);

        if (dsp.readStatus()) {
            Intent i = new Intent(DoctorloginActivity.this, Dashboarddoctor.class);
            startActivity(i);
            finish();
        }
        if (psp.readStatus()) {
            Intent i = new Intent(DoctorloginActivity.this, Dashboardpatient.class);
            startActivity(i);
            finish();
        }
        dloginusername = findViewById(R.id.logindusername);
        dloginpasswd = findViewById(R.id.logindpasswd);
        btndlogin = findViewById(R.id.dloginbtn);
        dtvaccount = findViewById(R.id.dmytv);
        switchpatient = findViewById(R.id.switchpatient);

        switchpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorloginActivity.this, PatientloginActivity.class);
                startActivity(i);
                finish();
            }
        });

        //For Underline in Textview
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        dtvaccount.setText(content);
        // End

        btndlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dloginusername.getText().toString().length() == 0 && dloginpasswd.getText().toString().length() == 0) {
                    dloginusername.setError("Username Must Required");
                    dloginpasswd.setError("Password Must Required");
                } else {
                    if (dloginusername.getText().toString().matches(emailPattern)) {

                        progressDialog = ProgressDialog.show(DoctorloginActivity.this, "Loading", "Please Wait . . .", false, false);
                        /*final Dialog d = new Dialog(DoctorloginActivity.this);
                        d.setContentView(R.layout.progressfile);
                        d.setCancelable(false);
                        d.show();*/

                        doc_username = dloginusername.getText().toString();
                        doc_passwd = dloginpasswd.getText().toString();

                        apiInterface = ApiClient.getAliClient().create(ApiInterface.class);
                        Call<List<GetterSetterDocDetailsForDashboard>> call = apiInterface.getDocLoginDetail(doc_username, doc_passwd);

                        call.enqueue(new Callback<List<GetterSetterDocDetailsForDashboard>>() {
                            @Override
                            public void onResponse(Call<List<GetterSetterDocDetailsForDashboard>> call, Response<List<GetterSetterDocDetailsForDashboard>> response) {
                                try {
                                    doc_login_details = response.body();

                                    doc_usernameNew = doc_login_details.get(0).getDoc_username();
                                    doc_passwdNew = doc_login_details.get(0).getDoc_passwd();

                                    if (doc_username.equals(doc_usernameNew) && doc_passwd.equals(doc_passwdNew)) {
                                        doc_id = doc_login_details.get(0).getDoc_id();
                                        doc_fname = doc_login_details.get(0).getDoc_fname();
                                        doc_lname = doc_login_details.get(0).getDoc_lname();
                                        doc_fullname = (doc_fname + " " + doc_lname);
                                        dsp.writeLoginStatus(true, doc_id, doc_fname, doc_lname);
                                        progressDialog.dismiss();
                                        Toast.makeText(DoctorloginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(DoctorloginActivity.this, Dashboarddoctor.class);
                                        i.putExtra("doc_fullname", doc_fullname);
                                        i.putExtra("doc_id", doc_id);
                                        startActivity(i);
                                        finish();
//                                      d.dismiss();
                                    }
                                } catch (Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(DoctorloginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<GetterSetterDocDetailsForDashboard>> call, Throwable t) {
                                Toast.makeText(DoctorloginActivity.this, "Invalid Credantials", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        dloginusername.setError("Username Isn't Valid");
                    }
                }
            }
        });

        dtvaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorloginActivity.this, DoctorRegistration.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(DoctorloginActivity.this)
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

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
