package com.example.full_project.Register;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.full_project.ApiClient;
import com.example.full_project.ApiInterface;
import com.example.full_project.Login.LoginActivity;
import com.example.full_project.R;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.N)
public class RegisterActivity extends AppCompatActivity {

    private TextView tv_login;
    private Button btnimage, btnregister;
    private EditText etemail, etfname, etlname, etpasswd;
    private ImageView imview;
    private final static int PERMISSION_REQ_CODE = 1;
    private final static int IMAGE_REQ_CODE = 2;
    Uri filepath;
    Bitmap bitmap;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    String mediaPath;
    private static final String UPLOAD_URL = "http://snatch.sumayinfotech.com/api/create-account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnimage = findViewById(R.id.r_btn_image);
        btnregister = findViewById(R.id.btn_register);
        etemail = findViewById(R.id.r_et_email);
        etpasswd = findViewById(R.id.r_et_passwd);
        etfname = findViewById(R.id.r_et_fname);
        etlname = findViewById(R.id.r_et_lname);
        imview = findViewById(R.id.r_imview);
        tv_login = findViewById(R.id.tv_register);

        checkPermission();

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(RegisterActivity.this, "Creating Account", "Please wait...", false, false);

                registerUser();
                Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void registerUser() {
        String email, fname, lname, passwd, imageurl;
        email = etemail.getText().toString();
        passwd = etpasswd.getText().toString();
        fname = etfname.getText().toString();
        lname = etlname.getText().toString();
        imageurl = getPath(filepath);


        String uploadid = UUID.randomUUID().toString();
        try {
            try {
                progressDialog.dismiss();
                new MultipartUploadRequest(this, uploadid, UPLOAD_URL)
                        .addParameter("email", email)
                        .addParameter("first_name", fname)
                        .addParameter("last_name", lname)
                        .addParameter("password", passwd)
                        .addFileToUpload(imageurl, "profile_pic")
                        .startUpload();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (FileUriExposedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_REQ_CODE && resultCode == RESULT_OK && data != null) {
            filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imview.setVisibility(View.VISIBLE);
                imview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);

        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , null
                , MediaStore.Images.Media._ID + " = ? "
                , new String[]{document_id}
                , null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private void chooseImage() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Open Image From"), IMAGE_REQ_CODE);
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, new String(Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQ_CODE);
        } else {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }
}
