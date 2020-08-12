package com.example.example_18_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator);
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> list  = new ArrayList<>();

        for(int i =0 ; i < 20; i++){
            list.add("item="+i);
        }

        recyclerView.setAdapter(new MyAdapter(list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(coordinatorLayout,"Snack bar",Snackbar.LENGTH_LONG).setAction("More action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Snack Action", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
