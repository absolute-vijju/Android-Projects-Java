package com.example.admin.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edusername,edemail,edpassword;
    Button btnsave;

    MyDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edusername=findViewById(R.id.edusername);
        edemail=findViewById(R.id.edemail);
        edpassword=findViewById(R.id.edpassword);

        btnsave=findViewById(R.id.btnsave);

        mydb=new MyDatabase(MainActivity.this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mydb.insertData(edusername.getText().toString(),edemail.getText().toString(),edpassword.getText().toString());
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
