package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class readActivity extends AppCompatActivity {
    TextView tv1,tv2;
    myHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        helper = new myHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select title, content from db_table",null);

        while(cursor.moveToNext()){
            String title = cursor.getString(0);
            String content = cursor.getString(1);

            tv1.setText("Title : " + title);
            tv2.setText("Content : " + content);
        }
    }
}
