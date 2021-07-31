package com.example.lenovo.buttons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rbmale, rbfemale;
    CheckBox chkread, chkplay;
    RatingBar ratingBar;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.rg);
        rbmale = findViewById(R.id.rb1);
        rbfemale = findViewById(R.id.rb2);
        chkplay = findViewById(R.id.chk1);
        chkread = findViewById(R.id.chk2);
        ratingBar=findViewById(R.id.rb);
        btn=findViewById(R.id.btn1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbmale.isChecked()) {
                    Toast.makeText(MainActivity.this, "Radiobutton: " + rbmale.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.rb2) {
                    Toast.makeText(MainActivity.this, "Radiobutton: " + rbfemale.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        chkplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkplay.isChecked()) {
                    Toast.makeText(MainActivity.this, "Checkbox: " + chkplay.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        chkread.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Check Box: " + chkread.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, "Rate: "+rating+" Star", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
                //finish();
            }
        });

    }
}
