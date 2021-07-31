package com.example.okhttp_demo;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyConnrection {

    JSONArray jsonArray;
    Request request;
    Response response;
    String data;

    public JSONArray getData(String url) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(url).build();
        response = okHttpClient.newCall(request).execute();
        data = response.body().string();
        jsonArray = new JSONArray(data);
        return jsonArray;
    }

}
