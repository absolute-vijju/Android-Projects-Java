package com.example.samplelogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 2;
    private EditText etlname, etfname, etemail, etpasswd;
    private Button btnregister, btnimage;
    private ImageView imview;
    private static final int IMAGE_REQ_CODE = 100;
    Uri uri;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etemail = findViewById(R.id.et_email);
        etpasswd = findViewById(R.id.et_passwd);
        etfname = findViewById(R.id.et_fname);
        etlname = findViewById(R.id.et_lname);
        imview = findViewById(R.id.imview);
        btnregister = findViewById(R.id.btn_register);
        btnimage = findViewById(R.id.btn_image);

        checkPermission();

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, IMAGE_REQ_CODE);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(MainActivity.this, "Creating Account", "Please wait...", false, false);
                registerUser();
            }
        });


    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
        } else {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {

        String email = etemail.getText().toString();
        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String passwd = etpasswd.getText().toString();

        String filepath = getRealPathFromURIPath(uri, MainActivity.this);

        File file = new File(filepath);
        Log.d("mydata", file.getName());

        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("pro_pic", file.getName(), mFile);
        RequestBody emailBody = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody fnameBody = RequestBody.create(MediaType.parse("text/plain"), fname);
        RequestBody lnameBody = RequestBody.create(MediaType.parse("text/plain"), lname);
        RequestBody passwdBody = RequestBody.create(MediaType.parse("text/plain"), passwd);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<MyResponse> call = apiInterface.addUser(fileToUpload, emailBody, fnameBody, lnameBody, passwdBody);

        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {

                MyResponse myResponse = response.body();

                Toast.makeText(MainActivity.this, "" + myResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*File file = new File(getRealPathFromURI(selectedImage));

        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(selectedImage)), file);
        RequestBody emailBody = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody fnameBody = RequestBody.create(MediaType.parse("text/plain"), fname);
        RequestBody lnameBody = RequestBody.create(MediaType.parse("text/plain"), lname);
        RequestBody passwdBody = RequestBody.create(MediaType.parse("text/plain"), passwd);*/


    }

    /*private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQ_CODE && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
        }
    }

    public String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
