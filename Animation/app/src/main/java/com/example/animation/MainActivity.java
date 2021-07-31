package com.example.animation;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnauto, btnscale;
    Button btnautorotate, btnrotate;
    Button btnautotranslate, btntranslate;
    Button btnautoalpha, btnalpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ;
        }

        btnauto = findViewById(R.id.btnautoscale);
        btnscale = findViewById(R.id.btnscale);

        btnautorotate = findViewById(R.id.btnautorotate);
        btnrotate = findViewById(R.id.btnrotate);

        btnautotranslate = findViewById(R.id.btnautotranslate);
        btntranslate = findViewById(R.id.btntranslate);

        btnautoalpha = findViewById(R.id.btnautoalpha);
        btnalpha = findViewById(R.id.btnalpha);

        final Animation autoscale = AnimationUtils.loadAnimation(MainActivity.this, R.anim.autoscaleanimation);
        btnauto.startAnimation(autoscale);

        btnscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scale = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scaleanimation);
                btnscale.startAnimation(scale);
            }
        });

        final Animation autorotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.autorotateanimation);
        btnautorotate.startAnimation(autorotate);

        btnrotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotateanimation);
                btnrotate.startAnimation(rotate);
            }
        });

        Animation autotranslate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.autotranslateanimation);
        btnautotranslate.startAnimation(autotranslate);

        btntranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation translate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translateanimation);
                btntranslate.startAnimation(translate);
            }
        });

        Animation autoalpha = AnimationUtils.loadAnimation(MainActivity.this, R.anim.autoalphaanimation);
        btnautoalpha.startAnimation(autoalpha);

        btnalpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation alpha = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alphaanimation);
                btnalpha.startAnimation(alpha);
            }
        });

    }
}
