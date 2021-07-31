package com.example.sqlite_practice;

import android.content.Intent;
import android.net.MailTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnsave, btndisplay;
    EditText etfname, etlname;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsave = findViewById(R.id.btnsave);
        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        btndisplay = findViewById(R.id.btndisplay);

        myDatabase = new MyDatabase(MainActivity.this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etfname.getText().toString().length() == 0 && etlname.getText().toString().length() == 0) {
                    etfname.setError("Firstname required");
                    etlname.setError("Lastname required");
                } else {
                    myDatabase.insertData(etfname.getText().toString(), etlname.getText().toString());
                    Toast.makeText(MainActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    etfname.getText().clear();
                    etlname.getText().clear();
                }
            }
        });

        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

    }
}
