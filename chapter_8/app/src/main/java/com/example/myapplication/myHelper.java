package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 2;

    public myHelper(Context context){
        super(context,"db_table",null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "create table db_table (" +
                "_id integer primary key autoincrement," +
                "title," +
                "content)";
        db.execSQL(memoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DB_VERSION){
            db.execSQL("drop table db_table");
            onCreate(db);
        }
    }
}
