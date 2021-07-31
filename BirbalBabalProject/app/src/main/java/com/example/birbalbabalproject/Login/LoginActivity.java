package com.example.birbalbabalproject.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birbalbabalproject.ApiClient;
import com.example.birbalbabalproject.ApiInterface;
import com.example.birbalbabalproject.R;
import com.example.birbalbabalproject.Register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email, et_passwd;
    private Button btn_login;
    private TextView tv_login;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponent();

        tv_login.setOnClickListener(new View.OnClickListener() {
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
                progressDialog = ProgressDialog.show(LoginActivity.this, "Logging In", "Please wait...", false, false);
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = et_email.getText().toString();
        String passwd = et_passwd.getText().toString();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiInterface.loginUser(email, passwd);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse data = response.body();
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();

                if (data.isSuccess()) {

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initComponent() {
        et_email = findViewById(R.id.l_etemail);
        et_passwd = findViewById(R.id.l_etpasswd);
        btn_login = findViewById(R.id.btn_login);
        tv_login = findViewById(R.id.tv_login);
    }
}
