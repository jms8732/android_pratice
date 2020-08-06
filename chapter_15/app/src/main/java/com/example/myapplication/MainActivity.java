package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    Button btn1,btn2;
    ArrayList<String> action = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onStart() {
        super.onStart();
        action.add("OnStart....");
    }

    @Override
    protected void onResume() {
        super.onResume();
        action.add("OnResume....");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        action.add("onSaveInstanceState....");
        outState.putStringArrayList("value",action);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        action = savedInstanceState.getStringArrayList("value");
        action.add("onRestoreInstaceState....");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,action);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1= (Button)findViewById(R.id.btn1);
        btn2=  (Button)findViewById(R.id.btn2);

        listView = (ListView)findViewById(R.id.listView);
        action.add("OnCreate....");

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,action);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent= null;
        if(v == btn1){
            intent = new Intent(this,DetailActivity.class);
        }else{
            intent = new Intent(this,DialogActivity.class);
        }

        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        action.add("OnPause....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        action.add("OnStop....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        action.add("OnDestory....");
    }
}