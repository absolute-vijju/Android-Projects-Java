package com.example.first_project.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first_project.ApiClient;
import com.example.first_project.ApiInterface;
import com.example.first_project.CheckInternet;
import com.example.first_project.Dashboard.DashboardActivity;
import com.example.first_project.R;
import com.example.first_project.Registration.MainActivity;
import com.example.first_project.SharedPreferenceFile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout l_til_email, l_til_passwd;
    EditText l_et_email, l_et_passwd;
    Button btnlogin;
    ApiInterface apiInterface;
    TextView tv_login;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    CheckInternet checkInternet;
    SharedPreferenceFile spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkInternet = new CheckInternet(this);
        tv_login = findViewById(R.id.tv_login);
        l_til_email = findViewById(R.id.l_til_email);
        l_til_passwd = findViewById(R.id.l_til_passwd);
        l_et_email = findViewById(R.id.l_et_email);
        l_et_passwd = findViewById(R.id.l_et_passwd);
        btnlogin = findViewById(R.id.btnlogin);

        if (checkInternet.getResult()) {
            Snackbar.make(btnlogin, "Internet Connected", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(btnlogin, "Internet Not Connected", Snackbar.LENGTH_SHORT).show();
        }

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email = l_et_email.getText().toString().trim();
        String password = l_et_passwd.getText().toString().trim();
        boolean check = true;

        if (l_et_email.getText().toString().isEmpty()) {
            l_til_email.setError("Email Mandatory");
            check = false;
        } else {
            l_til_email.setErrorEnabled(false);
        }

        if (l_et_email.getText().toString().matches(emailPattern)) {
            l_til_email.setErrorEnabled(false);
        } else {
            l_til_email.setError("Invalid Email");
            check = false;
        }

        if (l_et_passwd.getText().toString().isEmpty()) {
            l_til_passwd.setError("Password Mandatory");
            check = false;
        } else {
            l_til_passwd.setErrorEnabled(false);
        }

        if (check == true) {
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Checking Information", "Please wait...", false, false);
            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

            Call<Login> call = apiInterface.loginUser(email, password);

            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {

                    Login login = response.body();
                    progressDialog.dismiss();
                    String message = login.getMessage();
                    Log.d("mydata", message);


                    Toast.makeText(LoginActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                    if (message.equals("Login Successfull.")) {
                        Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(i);
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.d("mydata", t.getMessage());
                }
            });
        }


    }
}
