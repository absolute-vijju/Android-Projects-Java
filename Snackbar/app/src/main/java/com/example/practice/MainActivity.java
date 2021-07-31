package com.example.practice;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        relativeLayout = findViewById(R.id.root);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(relativeLayout, "Saved Successfully", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar snackbar1 = Snackbar.make(relativeLayout, "Undo Successfully", Snackbar.LENGTH_SHORT);
                                View view = snackbar1.getView();
                                TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextColor(Color.CYAN);
                                snackbar1.show();
                            }
                        })
                        .setActionTextColor(Color.GREEN);
                View view = snackbar.getView();
                TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }
        });
    }
}
