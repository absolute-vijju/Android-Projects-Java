package com.example.admin.sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    MyDatabase mydb;
    TextView uname;

    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        uname = findViewById(R.id.tvuname);

        mydb = new MyDatabase(SecondActivity.this);

        c = mydb.getData();

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {

                    String username = c.getString(1);
                   /* String email=c.getString(2);
                    String password=c.getString(3);
*/
                    uname.setText(username);
//                    Log.d("data","username : "+username);
  /*                  Log.d("data","email : "+email);
                    Log.d("data","password : "+password);
*/
                } while (c.moveToNext());
            }
        }

    }
}
