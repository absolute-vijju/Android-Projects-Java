package com.example.validation_materialdesign;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    EditText etusername, etpasswd;
    TextInputLayout ilusername, ilpasswd;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername = findViewById(R.id.etusername);
        etpasswd = findViewById(R.id.etpasswd);
        ilusername = findViewById(R.id.ilusername);
        ilpasswd = findViewById(R.id.ilpasswd);
        btnlogin = findViewById(R.id.btnlogin);
        ll = findViewById(R.id.ll);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                if (etusername.getText().toString().isEmpty()) {
                    ilusername.setError("Username mandatory");
                    isValid = false;
                } else {
                    ilusername.setErrorEnabled(false);
                }
                if (etpasswd.getText().toString().length() < 8) {
                    ilpasswd.setError("Minimum 8 characters required.");
                    isValid = false;
                } else {
                    ilpasswd.setErrorEnabled(false);
                }

                if (isValid == true) {
                    Snackbar snackbar = Snackbar.make(ll, "Login Successfully", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

    }
}
