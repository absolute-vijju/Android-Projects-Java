package com.example.implicit_intent;

import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnmap, btnmarket, btnmail, btnimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnmap = findViewById(R.id.btnmap);
        btnmarket = findViewById(R.id.btnmarket);
        btnmail = findViewById(R.id.btnmail);
        btnimage = findViewById(R.id.btnimage);

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:23.085123, 72.571578"));
                Intent.createChooser(intent, "Choose Map For Action");
                startActivity(intent);
            }
        });

        btnmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("market://details?id=com.supercell.clashofclans&hl=en"));
                Intent.createChooser(i, "Launch Market");
                startActivity(i);
            }
        });

        btnmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String to[] = {"vkoshti86@gmail.com", "vkoshti37@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL, to);
                i.putExtra(Intent.EXTRA_SUBJECT, "TEST SUBJECT");
                i.putExtra(Intent.EXTRA_TEXT, "This is simple mail for the testing purpose");
                i.setType("message/rfc822");
                Intent.createChooser(i, "Select App for Mail");
                startActivity(i);
            }
        });

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imageuri = Uri.parse("android.resource://com.example.implicit_intent/drawable" + R.drawable.rdj);
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, imageuri);
                i.putExtra(Intent.EXTRA_TEXT, "This is sample Image for Testing Purpose.");
                Intent.createChooser(i, "Select App");
                startActivity(i);
            }
        });
    }
}
