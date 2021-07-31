package com.example.full_project.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.full_project.Change_Password.ChangepasswordActivity;
import com.example.full_project.Edit_Profile.EditprofileActivity;
import com.example.full_project.Forget_Password.ForgetpasswordActivity;
import com.example.full_project.Login.LoginActivity;
import com.example.full_project.LoginSharedPreferenceFile;
import com.example.full_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity {

    private CardView cv_profile, cv_change_password, cv_forget_password;
    private LoginSharedPreferenceFile spfile;
    private FloatingActionButton fab;
    String userData[];
    private String pro_pic_url;
    private ImageView imview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cv_profile = findViewById(R.id.cv1);
        cv_change_password = findViewById(R.id.cv2);
        cv_forget_password = findViewById(R.id.cv3);
        fab = findViewById(R.id.fab_logout);
        imview = findViewById(R.id.dashboard_imview);
        spfile = new LoginSharedPreferenceFile(DashboardActivity.this);

        userData = spfile.getData();

        pro_pic_url = userData[5];

        Picasso.with(this).load(pro_pic_url).into(imview);

        /*for (int i = 0; i < userData.length; i++) {
            Log.d("mydata", userData[i]);
        }*/

        cv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, EditprofileActivity.class);
                startActivity(i);

            }
        });

        cv_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, ChangepasswordActivity.class);
                startActivity(i);
            }
        });

        cv_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, ForgetpasswordActivity.class);
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spfile.clearData();
                Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
