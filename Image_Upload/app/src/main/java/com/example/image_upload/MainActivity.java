package com.example.image_upload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText ettitle;
    Button btnchoose, btnupload;
    ImageView img;
    private static final int IMG_REQUEST = 777;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ettitle = findViewById(R.id.img_title);
        btnchoose = findViewById(R.id.btnchoose);
        btnupload = findViewById(R.id.btnupload);
        img = findViewById(R.id.imageview);

        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(MainActivity.this, "Uploading Image", "Please Wait. . .", false, false);
                uploadImage();
            }
        });

    }

    public void selectImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMG_REQUEST);
    }

    public String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    public void uploadImage() {
        String Image = imageToString();
        String Title = ettitle.getText().toString();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ImageClass> call = apiInterface.uploadImage(Title, Image);
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
                ImageClass imageClass = response.body();
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Image Uploaded Successfully. . .", Toast.LENGTH_SHORT).show();
                img.setVisibility(View.GONE);
                ettitle.setVisibility(View.GONE);
                btnchoose.setEnabled(true);
                btnupload.setEnabled(false);
                ettitle.setText("");
            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Image Uploaded Failed. . .", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
                ettitle.setVisibility(View.VISIBLE);
                btnchoose.setEnabled(false);
                btnupload.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
