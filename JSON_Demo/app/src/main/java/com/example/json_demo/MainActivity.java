package com.example.json_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {


    /*{"married":false,"name":"Vijay","salary":56000}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson=new Gson();

        Employee employee=new Employee("Vijay",56000,false);
        String JSON=gson.toJson(employee);

        Log.d("mydata",JSON);

    }
}
