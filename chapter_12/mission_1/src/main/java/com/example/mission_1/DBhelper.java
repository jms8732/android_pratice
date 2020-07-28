package com.example.mission_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    private static int version = 1;
    public DBhelper(Context context){
        super(context,"db_table",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table db_table "
                + "(_id integer primary key autoincrement," +
                "person," +
                "name," +
                "content)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == version){
            db.execSQL("drop table db_table");
            onCreate(db);
        }
    }
}
