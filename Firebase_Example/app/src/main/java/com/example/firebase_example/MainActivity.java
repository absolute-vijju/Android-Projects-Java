package com.example.firebase_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    EditText etname, etemail;
    Button btnsave;

    Firebase fbmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        btnsave = findViewById(R.id.btnsave);

        fbmain.setAndroidContext(MainActivity.this);
        fbmain = new Firebase("https://myproject-ed0e0.firebaseio.com/");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase name = fbmain.child("name");
                name.setValue(etname.getText().toString());
                Firebase email = fbmain.child("email");
                email.setValue(etemail.getText().toString());
                Toast.makeText(MainActivity.this, "Data Successfully Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
