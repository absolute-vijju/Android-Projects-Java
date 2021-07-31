package com.example.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rev;

    List<Contact> contacts;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rev = findViewById(R.id.rev);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rev.setLayoutManager(layoutManager);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        final Call<List<Contact>> details = apiInterface.getContact();

        details.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contacts = response.body();
                progressDialog.dismiss();
                MyAdapter my = new MyAdapter(contacts);
                rev.setAdapter(my);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("error", t.getMessage());
            }
        });
    }
}
