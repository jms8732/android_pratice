package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    List<String> detail = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = (ListView) findViewById(R.id.listview);

        intent = getIntent();
        ArrayAdapter<String> adapter = null;

        String city = intent.getStringExtra("city");

        if (city.equals("서울특별시")) {
            detail.add("종로구");
            detail.add("강남구");
            detail.add("송파구");

        } else {
            detail.add("구리시");
            detail.add("평택시");
            detail.add("과천시");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, detail);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = getIntent();
        String location = intent.getStringExtra("city") + detail.get(position);

        intent.putExtra("location",location);
        setResult(20,intent);
        finish();
    }
}
