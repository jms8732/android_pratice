package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout)findViewById(R.id.drawable);

        toggle = new ActionBarDrawerToggle(this,drawer,R.string.drawer_open,R.string.drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.navigator);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Toast toast = null;
                if(id == R.id.menu1){
                    toast = Toast.makeText(getApplicationContext(),"메뉴 1",Toast.LENGTH_SHORT);
                }else if(id == R.id.menu2) {
                    toast = Toast.makeText(getApplicationContext(),"메뉴 2",Toast.LENGTH_SHORT);
                }else{
                    toast = Toast.makeText(getApplicationContext(),"메뉴 3",Toast.LENGTH_SHORT);
                }
                toast.show();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return false;

        return super.onOptionsItemSelected(item);
    }
}