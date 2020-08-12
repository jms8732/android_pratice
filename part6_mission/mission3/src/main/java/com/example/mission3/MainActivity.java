package com.example.mission3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String [] person_name = {"안영주","최은경","최호성","정성택","정길용","임윤정","김종덕","김종찬"};
    private String [] date = {"2017-07-01","2017-07-01","2017-07-01","2017-06-30","2017-06-30","2017-06-29","2017-06-29","2017-06-29"};
    List<ItemVO> list;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycle);

        DBHelper helper = new DBHelper(this,person_name,date);
        SQLiteDatabase db = helper.getReadableDatabase();
        list=  new ArrayList<>();

        list.add(new HeadItem("오늘"));

        Cursor current = db.rawQuery("select * from db_table where date = '2017-07-01'",null);

        while(current.moveToNext()){
            list.add(new BodyItem(current.getString(2),current.getString(1)));
        }

        list.add(new HeadItem("어제"));

        Cursor yesterday = db.rawQuery("select * from db_table where date = '2017-06-30'",null);

        while(yesterday.moveToNext()){
            list.add(new BodyItem(yesterday.getString(2),yesterday.getString(1)));
        }

        list.add(new HeadItem("이전"));

        Cursor preivous = db.rawQuery("select * from db_table where date != '2017-07-01' and date != '2017-06-30'",null);

        while(preivous.moveToNext()){
            list.add(new BodyItem(preivous.getString(2),preivous.getString(1)));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this,list));
        recyclerView.addItemDecoration(new MyDecoration(list));
    }

}
