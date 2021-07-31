package com.example.mydoctorapp;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mydoctorapp.Doctor.DoctorloginActivity;

public class SliderActivity extends AppCompatActivity {

    ViewPager mpaPager;
    int layouts[] = {R.layout.first_slide, R.layout.second_slide, R.layout.third_slide, R.layout.fourth_slide};
    MpagerAdapter mpagerAdapter;
    LinearLayout Dots_Layout;
    ImageView dots[];
    Button btnskip, btnnext;
    SliderSharedPreferenceFile sspf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        if (Build.VERSION.SDK_INT > 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        sspf = new SliderSharedPreferenceFile(SliderActivity.this);

        boolean test = sspf.getStatus();

        if (sspf.readStatus()) {
            Intent i = new Intent(SliderActivity.this, DoctorloginActivity.class);
            startActivity(i);
            finish();
        }

        mpaPager = findViewById(R.id.viewpager);
        Dots_Layout = findViewById(R.id.dotsLayout);
        btnskip = findViewById(R.id.btnskip);
        btnnext = findViewById(R.id.btnnext);

        mpagerAdapter = new MpagerAdapter(layouts, SliderActivity.this);
        mpaPager.setAdapter(mpagerAdapter);

        createDots(0);

        mpaPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                createDots(i);
                if (i == layouts.length - 1) {
                    btnnext.setText("Let's Start! ");
                    btnskip.setVisibility(View.INVISIBLE);
                } else {
                    btnnext.setText("Next");
                    btnskip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationskip = AnimationUtils.loadAnimation(SliderActivity.this, R.anim.skip_button);
                btnskip.startAnimation(animationskip);
                sspf.writeLoginStatus(true);
                Intent i = new Intent(SliderActivity.this, DoctorloginActivity.class);
                startActivity(i);
                finish();
            }
        });

        Animation animationnext = AnimationUtils.loadAnimation(SliderActivity.this, R.anim.next_button);
        btnnext.startAnimation(animationnext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextSlide = mpaPager.getCurrentItem() + 1;
                if (nextSlide < layouts.length) {
                    mpaPager.setCurrentItem(nextSlide);
                } else {
                    sspf.writeLoginStatus(true);
                    Intent i = new Intent(SliderActivity.this, DoctorloginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    public void createDots(int currentPosition) {
        if (Dots_Layout != null) {
            Dots_Layout.removeAllViews();
        }

        dots = new ImageView[layouts.length];

        for (int i = 0; i < layouts.length; i++) {
            dots[i] = new ImageView(SliderActivity.this);
            if (i == currentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(SliderActivity.this, R.drawable.active_dots));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(SliderActivity.this, R.drawable.inactive_dots));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);

            Dots_Layout.addView(dots[i], params);
        }
    }
}
