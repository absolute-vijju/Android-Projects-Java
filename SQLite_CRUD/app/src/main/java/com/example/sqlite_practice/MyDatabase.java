package com.example.sqlite_practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.PortUnreachableException;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(Context context) {
        super(context, "MyDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_user(_id INTEGER PRIMARY KEY AUTOINCREMENT,firstname VARCHAR(20),lastname VARCHAR(20))");
    }

    public void insertData(String fname, String lname) {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname", fname);
        cv.put("lastname", lname);

        sd.insert("tbl_user", null, cv);
    }

    public Cursor getData() {
        SQLiteDatabase sd = getReadableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM tbl_user", null);
        return c;
    }

    public void deleteData(String id) {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("_id", id);
        sd.delete("tbl_user", "_id=?", new String[]{id});
    }

    public void updateData(String id, String fname, String lname) {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("_id", id);
        cv.put("firstname", fname);
        cv.put("lastname", lname);
        sd.update("tbl_user", cv, "_id='" + id + "'", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
