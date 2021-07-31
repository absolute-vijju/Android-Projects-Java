package com.example.autocompleteedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    TextView tv;
    Button btnshow;
    String names[] = {"Hardik", "Mona", "Diya", "Ajju", "Viya", "Nehal", "Sumit", "Mehul", "Vijay", "Vraj", "Komal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.actv);
        tv = findViewById(R.id.tv);
        btnshow = findViewById(R.id.btnshow);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        autoCompleteTextView.setAdapter(arrayAdapter);

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(autoCompleteTextView.getText().toString());
            }
        });

    }
}
