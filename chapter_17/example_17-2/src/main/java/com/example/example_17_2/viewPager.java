package com.example.example_17_2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class viewPager extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;

    public viewPager(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> list) {
        super(fm, behavior);

        this.fragments = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment cur_fragment = fragments.get(position);
        return cur_fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
