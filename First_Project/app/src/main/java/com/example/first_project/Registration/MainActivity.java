package com.example.first_project.Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first_project.ApiClient;
import com.example.first_project.ApiInterface;
import com.example.first_project.CheckInternet;
import com.example.first_project.Login.LoginActivity;
import com.example.first_project.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText et_email, et_fname, et_lname, et_passwd;
    Button btnimage, btnsave;
    TextInputLayout r_til_email, r_til_fname, r_til_lname, r_til_paswd;
    int IMAGE_REQUEST = 10;
    Bitmap bitmap;
    ImageView imview;
    ApiInterface apiInterface;
    TextView tv_reg;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    CheckInternet checkInternet;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInternet = new CheckInternet(this);
        r_til_email = findViewById(R.id.r_til_email);
        r_til_fname = findViewById(R.id.r_til_fname);
        r_til_lname = findViewById(R.id.r_til_lname);
        r_til_paswd = findViewById(R.id.r_til_paswd);
        et_email = findViewById(R.id.et_email);
        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        et_passwd = findViewById(R.id.et_passwd);
        btnimage = findViewById(R.id.btnimage);
        btnsave = findViewById(R.id.btnsave);
        imview = findViewById(R.id.imview);
        tv_reg = findViewById(R.id.tv_registration);

        if (checkInternet.getResult()) {
            Snackbar.make(btnsave, "Internet Connected", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(btnsave, "Internet Not Connected", Snackbar.LENGTH_SHORT).show();
        }

        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


    }

    private void saveData() {
        boolean check = true;
        String email = et_email.getText().toString().trim();
        String fname = et_fname.getText().toString().trim();
        String lname = et_lname.getText().toString().trim();
        String passwd = et_passwd.getText().toString().trim();
        String image = convertToString();

        Log.d("mydata", image);

        if (email.isEmpty()) {
            r_til_email.setError("Email Required");
            check = false;
        } else {
            r_til_email.setErrorEnabled(false);
        }

        if (email.matches(emailPattern)) {
            r_til_email.setErrorEnabled(false);
        } else {
            r_til_email.setError("Invalid Email");
            check = false;
        }

        if (fname.isEmpty()) {
            r_til_fname.setError("Firstname Required");
            check = false;
        } else {
            r_til_fname.setErrorEnabled(false);
        }

        if (lname.isEmpty()) {
            r_til_lname.setError("Lastname Required");
            check = false;
        } else {
            r_til_lname.setErrorEnabled(false);
        }

        if (passwd.isEmpty()) {
            r_til_paswd.setError("Password Required");
            check = false;
        } else {
            r_til_paswd.setErrorEnabled(false);
        }

        if (check == true) {
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Creating Account", "Please wait...", false, false);

            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

            Call<Registration> call = apiInterface.insertData(email, fname, lname, passwd, image);

            call.enqueue(new Callback<Registration>() {
                @Override
                public void onResponse(Call<Registration> call, Response<Registration> response) {
                    Registration registration = response.body();
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "" + registration.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Registration> call, Throwable t) {
                    Log.d("mydata", t.getMessage());
                    progressDialog.dismiss();
                }
            });
        }


    }


    private void getImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imview.setVisibility(View.VISIBLE);
                imview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
