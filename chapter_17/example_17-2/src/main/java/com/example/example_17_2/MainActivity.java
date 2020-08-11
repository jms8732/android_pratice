package com.example.example_17_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.pager);

        oneFragment oneFragment =new oneFragment();
        threeFragment threeFragment = new threeFragment();

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(oneFragment);
        list.add(threeFragment);

        FragmentManager manager = getSupportFragmentManager();
        viewPager p = new viewPager(manager,1,list);

        pager.setAdapter(p);
    }
}
