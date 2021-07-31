package com.example.swipe_delete;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("Slider")
    Call<SliderData> getSlider();

}
