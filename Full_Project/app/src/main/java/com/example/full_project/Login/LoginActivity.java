package com.example.full_project.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.full_project.ApiClient;
import com.example.full_project.ApiInterface;
import com.example.full_project.Dashboard.DashboardActivity;
import com.example.full_project.LoginSharedPreferenceFile;
import com.example.full_project.R;
import com.example.full_project.Register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_create;
    private EditText et_email, et_passwd;
    private Button btn_login;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    LoginSharedPreferenceFile spfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_create = findViewById(R.id.tv_login);
        et_email = findViewById(R.id.l_et_email);
        et_passwd = findViewById(R.id.l_et_passwd);
        btn_login = findViewById(R.id.btnlogin);
        spfile = new LoginSharedPreferenceFile(LoginActivity.this);

        if (spfile.getStatus()) {
            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(i);
            finish();
        }

        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(LoginActivity.this, "Logging", "Please wait...", false, false);
                loginUser();
            }
        });


    }

    private void loginUser() {
        String email = et_email.getText().toString();
        String passwd = et_passwd.getText().toString();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        final Login login = new Login(email, passwd);

        Call<Login> call = apiInterface.loginUser(login);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                /*if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "No Response from Server" + response.code(), Toast.LENGTH_SHORT).show();
                } else {*/
                progressDialog.dismiss();
                Login loginResponse = response.body();
                String messge = loginResponse.getMessage();
                boolean sucess = loginResponse.isSuccess();
                Log.d("mydata", String.valueOf(sucess));
                LoginData loginData = loginResponse.getLoginData();

                if (sucess) {
                    String user_id = loginData.getUser_id();
                    String email = loginData.getEmail();
                    String fname = loginData.getFirst_name();
                    String lname = loginData.getLast_name();
                    String token = loginData.getToken();
                    String pro_pic = loginData.getProfile_pic();

                    spfile.writeData(user_id, email, fname, lname, token, pro_pic, true);

                    Toast.makeText(LoginActivity.this, "" + messge, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(i);
                    finish();

//                    Log.d("mydata", user_id + "\n" + email + "\n" + fname + "\n" + lname + "\n" + pro_pic);

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
            /*}*/

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("mydata", t.getMessage());
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
