package com.example.dummyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rev;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rev = findViewById(R.id.rev);
        rev.setLayoutManager(new LinearLayoutManager(this));

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonblob.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        APiInterface aPiInterface = retrofit.create(APiInterface.class);

        Call<ModelClass> call = aPiInterface.getData();

        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                ModelClass modelClass = response.body();

                MyAdapter myAdapter = new MyAdapter(MainActivity.this, modelClass);
                rev.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable throwable) {
                Log.d("mydata", throwable.getMessage());
                Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
