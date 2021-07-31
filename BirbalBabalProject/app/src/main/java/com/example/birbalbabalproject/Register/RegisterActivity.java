package com.example.birbalbabalproject.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birbalbabalproject.ApiClient;
import com.example.birbalbabalproject.ApiInterface;
import com.example.birbalbabalproject.Login.LoginActivity;
import com.example.birbalbabalproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_name, et_mobile, et_email, et_password, et_comp_name, et_location, et_cntry_code, et_comp_address, et_city, et_state, et_pincode, et_locality, et_country, et_web_url, et_gst_in;
    private String name, mobile, email, passwd, company, location, country_code, com_address, city, state, pincode, locality, country, weburl, gstin;
    private Button btnregister;
    private TextView tv_registr;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();

        tv_registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(RegisterActivity.this, "Creating Account", "Please wait...", false, false);
                registerUser();
            }
        });
    }

    private void registerUser() {
        name = et_name.getText().toString();
        mobile = et_mobile.getText().toString();
        email = et_email.getText().toString();
        passwd = et_password.getText().toString();
        company = et_comp_name.getText().toString();
        location = et_location.getText().toString();
        country_code = et_cntry_code.getText().toString();
        com_address = et_comp_address.getText().toString();
        city = et_city.getText().toString();
        state = et_state.getText().toString();
        pincode = et_pincode.getText().toString();
        locality = et_locality.getText().toString();
        country = et_country.getText().toString();
        weburl = et_web_url.getText().toString();
        gstin = et_gst_in.getText().toString();

        /*Log.d("mydata", name + mobile + email + passwd + company + location + country_code + com_address + city
                + state + pincode + locality + country + weburl + gstin);*/

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        /*RegisterResponse registerResponse = new RegisterResponse(name, mobile, email, passwd, company, location, country_code, com_address, city, state, pincode, locality, country, weburl, gstin);*/

        Call<RegisterResponse> call = apiInterface.addUser(name, mobile, email, passwd, company, location, country_code, com_address, city, state, pincode, locality, country, weburl, gstin);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse responseData = response.body();

                progressDialog.dismiss();
                Log.d("mydata", responseData.getMessage() + "\n" + responseData.getOtp());
                Toast.makeText(RegisterActivity.this, "" + responseData.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("mydata", t.getMessage());
                Toast.makeText(RegisterActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initComponents() {
        et_name = findViewById(R.id.r_et_name);
        et_mobile = findViewById(R.id.r_et_number);
        et_email = findViewById(R.id.r_et_email);
        et_password = findViewById(R.id.r_et_passwd);
        et_comp_name = findViewById(R.id.r_et_company);
        et_location = findViewById(R.id.r_et_location);
        et_cntry_code = findViewById(R.id.r_et_countrycode);
        et_comp_address = findViewById(R.id.r_et_comaddress);
        et_city = findViewById(R.id.r_et_city);
        et_state = findViewById(R.id.r_et_state);
        et_pincode = findViewById(R.id.r_et_pincode);
        et_locality = findViewById(R.id.r_et_locality);
        et_country = findViewById(R.id.r_et_country);
        et_web_url = findViewById(R.id.r_et_weburl);
        et_gst_in = findViewById(R.id.r_et_gstin);
        btnregister = findViewById(R.id.btn_register);
        tv_registr = findViewById(R.id.tv_register);
    }
}
