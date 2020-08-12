package com.example.mission_3;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String name = "db_table";
    private String [] person_name = {"안영주","최은경","최호성","정성택","정길용","임윤정","김종덕","김종찬"};
    private String [] date = {"2017-07-01","2017-07-01","2017-07-01","2017-06-30","2017-06-30","2017-06-29","2017-06-29","2017-06-29"};


    public DBHelper(Context context){
        super(context, name, null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table db_table " +
                "(_id integer primary key autoincrement," +
                "date," +
                "name)";

        db.execSQL(sql);

        for(int i =0 ; i < date.length ; i++){
            db.execSQL("insert into db_table (date, name) values (?, ?)", new String[]{date[i],person_name[i]});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == VERSION){
            db.execSQL("drop table db_table");
            onCreate(db);
        }
    }
}
