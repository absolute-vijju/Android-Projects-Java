package com.example.birbalbabalproject.Slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.example.birbalbabalproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    Context context;
    List<ResponseArray> responseArray;
    private String image_base_url;

    public PagerAdapter(Context context, List<ResponseArray> responseArray, String image_base_url) {
        this.context = context;
        this.responseArray = responseArray;
        this.image_base_url = image_base_url;
    }

    @Override
    public int getCount() {
        return responseArray.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_rowfile, container, false);

        ImageView imageView = view.findViewById(R.id.imview);
        Picasso.with(context).load(image_base_url + responseArray.get(position).getImage()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + responseArray.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
