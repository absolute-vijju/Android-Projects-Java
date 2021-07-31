package com.example.lenovo.registrationform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Second extends AppCompatActivity {

    TextView fullname,gender,lang,email;
    String rfiname,rlname,rgendername,rlang,remail;
    Button welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fullname=findViewById(R.id.fname);
        gender=findViewById(R.id.gender);
        lang=findViewById(R.id.languages);
        email=findViewById(R.id.remail);
        welcome=findViewById(R.id.btnlogin);

        rfiname=getIntent().getStringExtra("firstname");
        rlname=getIntent().getStringExtra("lastname");
        rgendername=getIntent().getStringExtra("gender");
        rlang=getIntent().getStringExtra("lang");
        remail=getIntent().getStringExtra("email");


        fullname.setText(rfiname+" "+rlname);
        gender.setText(rgendername);
        lang.setText(rlang);
        email.setText(remail);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Second.this,Welcome.class);
                i.putExtra("fname",fullname.getText().toString());
                startActivity(i);
            }
        });


    }
}
