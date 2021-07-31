package com.example.retrofit_specific;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etid;
    Button btnsearch;
    TextView tvusername, tvnumber;

    ApiInterface apiInterface;
    List<Contacts> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etid = findViewById(R.id.etid);
        btnsearch = findViewById(R.id.btnsearch);
        tvusername = findViewById(R.id.tvusername);
        tvnumber = findViewById(R.id.tvnmber);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Contacts>> call = apiInterface.getSpecificData(etid.getText().toString());

                call.enqueue(new Callback<List<Contacts>>() {
                    @Override
                    public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                        try {
                            contacts = response.body();
                            tvusername.setText(contacts.get(0).getUsername());
                            tvnumber.setText(contacts.get(0).getNumber());
                        } catch (Exception e+) {
                            tvusername.setText("No data found.");
                            tvnumber.setText("No data found.");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Contacts>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
