package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button fr1,fr2,fr3;
    oneFragment oneFragment;
    twoFragment twoFragment;
    threeFragment threeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fr1 = (Button)findViewById(R.id.fragment1);
        fr2 = (Button)findViewById(R.id.fragment2);
        fr3 = (Button)findViewById(R.id.fragment3);

        fr1.setOnClickListener(this);
        fr2.setOnClickListener(this);
        fr3.setOnClickListener(this);

        oneFragment = new oneFragment();
        twoFragment = new twoFragment();
        threeFragment = new threeFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.main_container,oneFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft =null;
        if( v==fr1){
            if(!oneFragment.isVisible()) {
                ft = manager.beginTransaction();
                ft.replace(R.id.main_container, oneFragment);
                ft.commit();
            }
        }else if( v == fr2){
            twoFragment.show(manager,null);
        }else{
            if(!threeFragment.isVisible()) {
                ft = manager.beginTransaction();
                ft.replace(R.id.main_container, threeFragment);
                ft.commit();
            }
        }
    }
}