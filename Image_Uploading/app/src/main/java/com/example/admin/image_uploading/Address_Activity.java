package com.example.admin.image_uploading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Address_Activity extends AppCompatActivity
{



    String latitude,longitude;

    Button btngo;
    ///-------------------------


    String storeaddress="403, 4th Floor, Shivam Complex Bhuyangdev Char Rasta,, Sola Rd, Naranpura, Ahmedabad, Gujarat 380013";

    ///--------------------------------------

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_);

        pd=new ProgressDialog(Address_Activity.this);

        btngo= (Button) findViewById(R.id.btngo);

        btngo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String response;
                String address=storeaddress;

                HttpDataHander http=new HttpDataHander();
                String url=String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                response=http.GetHttpData(url);



                try {
                    JSONObject jobj=new JSONObject(response);
                    String lat=((JSONArray)jobj.get("results")).getJSONObject(0).getJSONObject("geometry")
                            .getJSONObject("location").get("lat").toString();
                    String lng=((JSONArray)jobj.get("results")).getJSONObject(0).getJSONObject("geometry")
                            .getJSONObject("location").get("lng").toString();


                    latitude= String.valueOf(Double.parseDouble(lat));
                    longitude= String.valueOf(Double.parseDouble(lng));

                    Log.d("data","latitude : "+latitude);
                    Log.d("data","longitude : "+longitude);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /*Geocoder geocoder = null;


                List<Address> addressList = null;
                try {
                    addressList = geocoder.getFromLocationName(
                            storeaddress, 5);

                    if (addressList != null && addressList.size() > 0)
                    {
                        latitude = String.valueOf((int) (addressList.get(0).getLatitude() * 1e6));
                        longitude = String.valueOf((int) (addressList.get(0).getLongitude() * 1e6));


                        Log.d("mydata",latitude);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/
            }
        });
    }
}
