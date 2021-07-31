package com.example.full_project.Forget_Password;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.full_project.ApiClient;
import com.example.full_project.ApiInterface;
import com.example.full_project.LoginSharedPreferenceFile;
import com.example.full_project.R;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetpasswordActivity extends AppCompatActivity {

    private String email;
    private String data[];
    private EditText et_email;
    private Button btnsend;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    LoginSharedPreferenceFile sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        et_email = findViewById(R.id.fp_et_email);
        btnsend = findViewById(R.id.fp_btnsend);
        sp = new LoginSharedPreferenceFile(ForgetpasswordActivity.this);

        data = sp.getData();

        email = data[1];
        et_email.setText(email);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(ForgetpasswordActivity.this
                        , "Sending Password"
                        , "Please wait..."
                        , false
                        , false);
                sendPassword();
            }
        });

    }

    private void sendPassword() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ForgetPassword> call = apiInterface.sendPassword(email);

        call.enqueue(new Callback<ForgetPassword>() {
            @Override
            public void onResponse(Call<ForgetPassword> call, Response<ForgetPassword> response) {
                progressDialog.dismiss();
                ForgetPassword data = response.body();

                if (data.isSuccess()) {
                    final Snackbar snackbar = Snackbar.make(btnsend, data.getMessage(), Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Okay", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                } else {
                    Toast.makeText(ForgetpasswordActivity.this, "Email Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgetPassword> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ForgetpasswordActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
