package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {
    DBHelper helper;
    TextView n,p,e;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        n = (TextView)findViewById(R.id.name);
        p = (TextView)findViewById(R.id.phone);
        e = (TextView)findViewById(R.id.email);

        helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String select = "select name, phone, email from my_db order by _id desc limit 1";

        Cursor cursor = db.rawQuery(select,null);

        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String phone = cursor.getString(1);
            String email = cursor.getString(2);

            n.setText("Name : " + name);
            p.setText("Phone : " +phone);
            e.setText("Email : " + email);
        }

    }
}
