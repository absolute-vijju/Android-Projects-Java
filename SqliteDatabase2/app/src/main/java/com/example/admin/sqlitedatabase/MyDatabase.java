package com.example.admin.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(Context context) {
        super(context, "mydb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tbldata (id integer primary key autoincrement,username varchar(20),email varchar(20),password varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(String e_username,String e_email,String e_password)
    {
        SQLiteDatabase sd=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",e_username);
        cv.put("email",e_email);
        cv.put("password",e_password);
        sd.insert("tbldata",null,cv);
    }

    public Cursor getData()
    {
        SQLiteDatabase sd=this.getReadableDatabase();
        Cursor c=sd.rawQuery("select * from tbldata",null);
        return c;
    }

}
