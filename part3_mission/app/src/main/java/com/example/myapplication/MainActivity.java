package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText n,p,e;
    Button btn1;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        n = (EditText)findViewById(R.id.name);
        p = (EditText)findViewById(R.id.phone);
        e = (EditText)findViewById(R.id.email);

        btn1.setOnClickListener(this);
        helper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn1){
            String name = n.getText().toString();
            String phone = p.getText().toString();
            String email = e.getText().toString();

            SQLiteDatabase db = helper.getWritableDatabase();

            if(name.isEmpty())
                Toast.makeText(this, "이름이 등록되지 않았습니다.", Toast.LENGTH_SHORT).show();
            else {
                String insert = "insert into my_db (name, phone, email) values (?, ?, ?)";

                db.execSQL(insert, new String[]{name, phone, email});

                Intent intent = new Intent(this, ReadActivity.class);
                startActivity(intent);
            }
        }
    }
}