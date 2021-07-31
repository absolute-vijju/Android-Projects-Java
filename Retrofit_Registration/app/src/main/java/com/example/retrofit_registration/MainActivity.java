package com.example.retrofit_registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etusername, etpasswd;
    TextView tvcreate, tvusername, tvpasswd;
    Button btnlogin;

    ApiInterface apiInterface;

    List<Contacts> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername = findViewById(R.id.etusername);
        etpasswd = findViewById(R.id.etpasswd);
        btnlogin = findViewById(R.id.btnlogin);
        tvcreate = findViewById(R.id.tvcreate);
        tvusername = findViewById(R.id.tvusername);
        tvpasswd = findViewById(R.id.tvpassword);

        tvcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistragionActivity.class);
                startActivity(i);
                finish();
            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Contacts>> call = apiInterface.getSpecificData(etusername.getText().toString(), etpasswd.getText().toString());
                call.enqueue(new Callback<List<Contacts>>() {
                    @Override
                    public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                        contacts = response.body();
                        tvusername.setText(contacts.get(0).getDoc_username());
                        tvpasswd.setText(contacts.get(0).getDoc_passwd());
                    }

                    @Override
                    public void onFailure(Call<List<Contacts>> call, Throwable t) {
                        Log.d("error",t.getMessage());
                    }
                });

            }
        });

    }
}
