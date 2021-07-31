package com.example.new_mapproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button btnlocation, btnaddress;
    TextView tv, tv2;
    LocationManager locationManager;
    LocationListener locationListener;
    double lat;
    double lon;
    private static final int REQ_CODE = 2;
    ProgressDialog progressDialog;
    Geocoder geocoder;
    List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlocation = findViewById(R.id.btn);
        btnaddress = findViewById(R.id.btngo);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                progressDialog.dismiss();
                lat = location.getLatitude();
                lon = location.getLongitude();
                tv.setText("Latitude: " + lat + "\n Longitude: " + lon);
                btnaddress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };


        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    progressDialog = ProgressDialog.show(MainActivity.this, "Locating Address", "Please wait...", false, false);
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    locationManager.requestLocationUpdates("gps", 100, 0, locationListener);
                } else {
                    Toast.makeText(MainActivity.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(lat, lon, 2);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String fulladdress = address.getAddressLine(0);
                        String fulladdress2 = address.getAddressLine(1);
                        tv2.setText("Address 1: " + fulladdress + "\nAddress 2: " + fulladdress2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQ_CODE);
            return false;
        } else {
            return true;
        }
    }
}
