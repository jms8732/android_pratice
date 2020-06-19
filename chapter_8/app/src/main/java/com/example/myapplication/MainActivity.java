package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button btn1;
    myHelper helper;
    EditText ti,ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.add);
        btn1.setOnClickListener(this);
        ti = (EditText)findViewById(R.id.title);
        ct = (EditText)findViewById(R.id.content);
        helper = new myHelper(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btn1){
            String title = ti.getText().toString();
            String content = ct.getText().toString();

            String insert = "insert into db_table (title, content) values (?,?)";
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL(insert,new String[]{title,content});
            db.close();

            Toast.makeText(this, "insert complete", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,readActivity.class);
            startActivity(intent);
        }
    }
}