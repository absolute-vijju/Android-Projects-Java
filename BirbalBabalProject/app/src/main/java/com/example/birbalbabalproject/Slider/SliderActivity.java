package com.example.birbalbabalproject.Slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.birbalbabalproject.ApiClient;
import com.example.birbalbabalproject.ApiInterface;
import com.example.birbalbabalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    ViewPager viewPager;
    Button btntest;
    PagerAdapter pagerAdapter;
    private LinearLayout dots_layout;
    private ImageView[] dots;
    List<ResponseArray> responseArray = new ArrayList<>();
    private int current_posision = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewPager = findViewById(R.id.vpager);
        dots_layout = findViewById(R.id.dots_layout);
        btntest=findViewById(R.id.btntest);

        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SliderActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<SliderResponse> call = apiInterface.getSliders();

        call.enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                SliderResponse sliderResponse = response.body();

                responseArray = sliderResponse.getResponseArray();

                /*for (int i = 0; i < responseArray.size(); i++) {
                    Log.d("mydata", responseArray.get(i).getId());
                    Log.d("mydata", responseArray.get(i).getName());
                    Log.d("mydata", responseArray.get(i).getImage());
                }
                Log.d("mydata", sliderResponse.getPath());*/

                pagerAdapter = new PagerAdapter(SliderActivity.this, responseArray, sliderResponse.getPath());
                viewPager.setAdapter(pagerAdapter);

                createDots(0);
                createSlideshow();

            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {
                Log.d("mydata", t.getMessage());
                Toast.makeText(SliderActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void createSlideshow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_posision == responseArray.size()) {
                    current_posision = 0;
                }
                viewPager.setCurrentItem(current_posision++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1000, 2000);
    }

    private void createDots(int current_position) {
        if (dots_layout != null) {
            dots_layout.removeAllViews();
        }

        dots = new ImageView[responseArray.size()];

        for (int i = 0; i < responseArray.size(); i++) {
            dots[i] = new ImageView(this);
            if (i == current_position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4, 4, 4, 4);

            dots_layout.addView(dots[i], layoutParams);
        }
    }
}
