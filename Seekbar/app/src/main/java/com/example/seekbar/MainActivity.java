package com.example.seekbar;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.ll);
        sb = findViewById(R.id.sk);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                /*if (progress >= 10) {
                    ll.setBackgroundColor(Color.RED);
                } else if (progress >= 20) {
                    ll.setBackgroundColor(Color.YELLOW);
                } else if (progress >= 30) {
                    ll.setBackgroundColor(Color.GREEN);
                } else if (progress >= 40) {
                    ll.setBackgroundColor(Color.GRAY);
                } else if (progress >= 50) {
                    ll.setBackgroundColor(Color.CYAN);
                } else if (progress >= 60) {
                    ll.setBackgroundColor(Color.MAGENTA);
                } else if (progress >= 70) {
                    ll.setBackgroundColor(Color.BLUE);
                } else if (progress >= 80) {
                    ll.setBackgroundColor(Color.LTGRAY);
                } else if (progress >= 90) {
                    ll.setBackgroundColor(Color.DKGRAY);
                } else {
                    ll.setBackgroundColor(Color.WHITE);
                }*/

                switch (progress) {
                    case 10:
                        ll.setBackgroundColor(Color.RED);
                        final Dialog d = new Dialog(MainActivity.this);
                        d.setContentView(R.layout.mydialog);
                        d.show();
                        d.setCancelable(false);
                        Button btn;
                        btn = d.findViewById(R.id.btn);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Process is now 10", Toast.LENGTH_SHORT).show();
                                d.dismiss();
                            }
                        });
                        break;
                    case 20:
                        ll.setBackgroundColor(Color.YELLOW);
                        Toast.makeText(MainActivity.this, "20", Toast.LENGTH_SHORT).show();
                        break;
                    case 30:
                        ll.setBackgroundColor(Color.GREEN);
                        Toast.makeText(MainActivity.this, "30", Toast.LENGTH_SHORT).show();
                        break;
                    case 40:
                        ll.setBackgroundColor(Color.GRAY);
                        Toast.makeText(MainActivity.this, "40", Toast.LENGTH_SHORT).show();
                        break;
                    case 50:
                        ll.setBackgroundColor(Color.CYAN);
                        Toast.makeText(MainActivity.this, "50", Toast.LENGTH_SHORT).show();
                        break;
                    case 60:
                        ll.setBackgroundColor(Color.MAGENTA);
                        Toast.makeText(MainActivity.this, "60", Toast.LENGTH_SHORT).show();
                        break;
                    case 70:
                        ll.setBackgroundColor(Color.BLUE);
                        Toast.makeText(MainActivity.this, "70", Toast.LENGTH_SHORT).show();
                        break;
                    case 80:
                        ll.setBackgroundColor(Color.LTGRAY);
                        Toast.makeText(MainActivity.this, "80", Toast.LENGTH_SHORT).show();
                    case 90:
                        ll.setBackgroundColor(Color.DKGRAY);
                        Toast.makeText(MainActivity.this, "90", Toast.LENGTH_SHORT).show();
                    default:
                        ll.setBackgroundColor(Color.WHITE);
                        Toast.makeText(MainActivity.this, "Default", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
