package com.example.full_project.Change_Password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.full_project.ApiClient;
import com.example.full_project.ApiInterface;
import com.example.full_project.LoginSharedPreferenceFile;
import com.example.full_project.R;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.full_project.ApiClient.BASE_URL;

public class ChangepasswordActivity extends AppCompatActivity {

    private EditText et_id, et_oldpasswd, et_newpasswd;
    private Button btnsave;
    private String userData[];
    private String user_id, old_passwd, new_passwd, token;
    LoginSharedPreferenceFile sp;
    private ApiInterface apiInterface;
    ChangePasswordData changePasswordData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        et_id = findViewById(R.id.cp_etid);
        et_oldpasswd = findViewById(R.id.cp_etoldpasswd);
        et_newpasswd = findViewById(R.id.cp_etnewpasswd);
        btnsave = findViewById(R.id.cp_btnchange_passwd);
        sp = new LoginSharedPreferenceFile(ChangepasswordActivity.this);

        userData = sp.getData();
        user_id = userData[0];
        token = userData[4];
        et_id.setText(user_id);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePassword();
            }
        });


    }

    private void savePassword() {

        old_passwd = et_oldpasswd.getText().toString();
        new_passwd = et_newpasswd.getText().toString();

        /*Log.d("mydata", token);
        Log.d("mydata", user_id);
        Log.d("mydata", old_passwd);
        Log.d("mydata", new_passwd);*/


       /* OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Beared " + token)
                        .build();

                return chain.proceed(newRequest);
            }
        }).build();*/

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ChangePasswordData> call = apiInterface.changePassword(token, user_id, old_passwd, new_passwd);

        call.enqueue(new Callback<ChangePasswordData>() {
            @Override
            public void onResponse(Call<ChangePasswordData> call, Response<ChangePasswordData> response) {
                changePasswordData = new ChangePasswordData();

                changePasswordData = response.body();

                if (response.isSuccessful()) {
                    Toast.makeText(ChangepasswordActivity.this, "" + changePasswordData.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangepasswordActivity.this, "" + changePasswordData.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordData> call, Throwable t) {
                Toast.makeText(ChangepasswordActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
