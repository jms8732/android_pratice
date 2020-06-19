package com.example.example_8_2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class readActivity extends AppCompatActivity {
    TextView tv1,tv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);


        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        Realm realm = Realm.getDefaultInstance();
        MemoVO vo = realm.where(MemoVO.class).findFirst();

        tv1.setText("Title : " + vo.title);
        tv2.setText("Content : " + vo.content);
    }
}
