package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView array, simple, cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        array = (ListView) findViewById(R.id.array);
        simple = (ListView) findViewById(R.id.simple);
        cursor = (ListView) findViewById(R.id.cursor);

        init(this);
    }

    private void init(Context context) {
        String[] datas = context.getResources().getStringArray(R.array.location);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, datas);
        array.setAdapter(arrayAdapter);


        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("name","류현진");
        map.put("content","제발 MLB에서 볼 수 있기를");

        map.put("name","강정호");
        map.put("content","야구로 보답하겠습니다");
        list.add(map);

        SimpleAdapter simpleAdapter =new SimpleAdapter(context,list,android.R.layout.simple_list_item_2,new String[]{"name","content"}, new int[]{android.R.id.text1,android.R.id.text2});
        simple.setAdapter(simpleAdapter);

        DBHelper helper =new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "insert into db_tb (name, content) values (?,?)";
        String [] name = {"류현진","강정호"};
        String [] content = {"제발 MLB에서 볼 수 있기를", "야구로 보답하겠습니다"};

        for(int i =0 ; i < 2 ; i++){
            String n = name[i];
            String cc = content[i];
            db.execSQL(sql,new String[]{n,cc});
        }

        Cursor c = db.rawQuery("select * from db_tb",null);

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,new String[]{"name","content"},new int[]{android.R.id.text1,android.R.id.text2}
        ,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);


        cursor.setAdapter(cursorAdapter);
    }


}