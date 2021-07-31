package com.example.lenovo.buttonbackgroundcolor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

public class MainActivity extends AppCompatActivity {


    Button btnblue;
    boolean checkblue=false;
    Button btnred;
    boolean checkred=false;
    Button btngreen;
    boolean checkgreen=false;
    Button btngray;
    boolean checkgray=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnblue=findViewById(R.id.btn1);
        btnred=findViewById(R.id.btn2);
        btngreen=findViewById(R.id.btn3);
        btngray=findViewById(R.id.btn4);

        btnblue.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {


                if(checkblue==false)
                {
                    btnblue.setBackgroundColor(Color.BLUE);
                    checkblue=true;
                }
                else
                {
                    checkblue=true;
                    btnblue.setBackgroundColor(Color.YELLOW);
                    checkblue=false;
                }
            }
        });

        btnred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkred==false)
                {
                    btnred.setBackgroundColor(Color.RED);
                    checkred=true;
                }
                else
                {
                    checkred=true;
                    btnred.setBackgroundColor(Color.YELLOW);
                    checkred=false;
                }
            }
        });

        btngreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkgreen==false)
                {
                    btngreen.setBackgroundColor(Color.GREEN);
                    checkgreen=true;
                }
                else
                {
                    checkgreen=true;
                    btngreen.setBackgroundColor(Color.YELLOW);
                    checkgreen=false;
                }
            }
        });

        btngray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkgray==false)
                {
                    btngray.setBackgroundColor(Color.GRAY);
                    checkgray=true;
                }
                else
                {
                    checkgray=true;
                    btngray.setBackgroundColor(Color.YELLOW);
                    checkgray=false;
                }
            }
        });
    }
}
