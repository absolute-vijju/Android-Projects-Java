package com.example.picasso_imageload;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button btnshow;
    ImageView imview, imview2;
    String url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
    String url2 = "http://snatch.sumayinfotech.com/media/2019/08/13/092427/2.1 Android Multiple Screen Support_ Project Setup";
    ProgressDialog progressDialog;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnshow = findViewById(R.id.btnshow);
        imview = findViewById(R.id.imview);
        imview2 = findViewById(R.id.imview2);

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(MainActivity.this, "Getting Image", "Please wait...", false, false);
                check = true;
                if (check == true) {
                    progressDialog.dismiss();
                    imview.setVisibility(View.VISIBLE);
                    Picasso.with(MainActivity.this)
                            .load(url)
                            .into(imview);
                    imview2.setVisibility(View.VISIBLE);
                    Picasso.with(MainActivity.this)
                            .load(url2)
                            .into(imview2);
                }
            }
        });


    }
}
