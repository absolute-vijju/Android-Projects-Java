package com.example.lenovo.registrationform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText fname, lname;
    RadioGroup gender;
    RadioButton rbmale, rbfemale, rbother;
    CheckBox chkeng, chkhin, chkguj;
    EditText email, passwd;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.etfname);
        lname = findViewById(R.id.etlname);
        gender = findViewById(R.id.rggender);
        rbmale = findViewById(R.id.rbmale);
        rbfemale = findViewById(R.id.rbfemale);
        rbother = findViewById(R.id.rbother);
        chkeng = findViewById(R.id.chkeng);
        chkhin = findViewById(R.id.chkhin);
        chkguj = findViewById(R.id.chkguj);
        email = findViewById(R.id.etemail);
        passwd = findViewById(R.id.etpasswd);
        btn=findViewById(R.id.btnsubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, Second.class);
                i.putExtra("firstname",fname.getText().toString());
                i.putExtra("lastname",lname.getText().toString());
                i.putExtra("gender",rbmale.getText().toString());
                i.putExtra("lang",chkeng.getText().toString());
                i.putExtra("email",email.getText().toString());
                startActivity(i);
            }
        });

    }
}
