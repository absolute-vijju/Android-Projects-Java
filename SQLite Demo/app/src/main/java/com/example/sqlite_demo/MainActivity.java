package com.example.sqlite_demo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fname, lname;
    Button btnsave, btnview;
    MyDatabase mydb = new MyDatabase(this);
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        btnsave = findViewById(R.id.btnsave);
        btnview = findViewById(R.id.btnview);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertdata(fname.getText().toString(), lname.getText().toString());
                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = mydb.getData();
                if (c.getColumnCount() > 0) {
                    if (c.moveToFirst()) {
                        do {
                            String fname = c.getString(1);
                            String lname = c.getString(2);
                            Toast.makeText(MainActivity.this, "" + fname + " " + lname, Toast.LENGTH_SHORT).show();
                        } while (c.moveToNext());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this).setTitle("Alert").setMessage("Are you sure want to quit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setCancelable(false).show();

    }
}
