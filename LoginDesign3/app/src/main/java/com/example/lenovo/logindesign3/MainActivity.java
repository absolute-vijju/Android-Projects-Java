package com.example.lenovo.logindesign3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText mail,passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn1);
        mail=findViewById(R.id.email);
        passwd=findViewById(R.id.passwd);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+mail.getText().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ""+passwd.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
