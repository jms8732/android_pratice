package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static final int VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context,"db_tb",null,VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == VERSION){
            db.execSQL("drop table db_tb",null);
            onCreate(db);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table db_tb (" +
                "_id integer primary key autoincrement," +
                "name," +
                "content)";

        db.execSQL(sql);
    }
}
