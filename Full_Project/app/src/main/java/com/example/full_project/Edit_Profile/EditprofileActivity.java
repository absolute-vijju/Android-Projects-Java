package com.example.full_project.Edit_Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.full_project.LoginSharedPreferenceFile;
import com.example.full_project.R;
import com.squareup.picasso.Picasso;

public class EditprofileActivity extends AppCompatActivity {

    private ImageView imview;
    private String id, fname, lname, pro_pic;
    private String[] userData;
    private EditText et_id, et_fname, et_lname;
    private Button btnsave;
    LoginSharedPreferenceFile sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        imview = findViewById(R.id.ep_imview);
        et_id = findViewById(R.id.ep_et_id);
        et_fname = findViewById(R.id.ep_et_fname);
        et_lname = findViewById(R.id.ep_et_lname);
        btnsave = findViewById(R.id.ep_btnsave_peofile);
        sp = new LoginSharedPreferenceFile(EditprofileActivity.this);


        userData = sp.getData();

        id = userData[0];
        et_id.setText(id);
        fname = userData[2];
        et_fname.setText(fname);
        lname = userData[3];
        et_lname.setText(lname);
        pro_pic = userData[5];
        Picasso.with(this).load(pro_pic).into(imview);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

    }

    private void saveProfile() {

    }
}
