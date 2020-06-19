package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static final int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context,"my_db",null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table my_db (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "phone," +
                "email)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == VERSION){
            db.execSQL("drop table my_db");
            onCreate(db);
        }
    }
}

