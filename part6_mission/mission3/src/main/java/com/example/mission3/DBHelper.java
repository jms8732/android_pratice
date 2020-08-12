package com.example.mission3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private String [] person_name, date;

    public DBHelper(Context context, String [] pn, String [] d){
        super(context,"db_table",null,1);
        this.person_name = pn;
        this.date = d;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table db_table" +
                "(_id integer primary key autoincrement," +
                "date," +
                "name)";

        db.execSQL(sql);


        for(int i =0 ; i < date.length ; i++){
            db.execSQL("insert into db_table (date, name) values (?,?)", new String[]{date[i],person_name[i]});
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == 1){
            onCreate(db);
        }
    }
}
