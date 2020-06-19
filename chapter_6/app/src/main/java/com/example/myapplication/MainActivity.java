package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    Switch sw1;
    CheckBox cb1,cb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw1 = (Switch)findViewById(R.id.sw1);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);

        sw1.setOnCheckedChangeListener(this);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView == sw1){

        }else if(buttonView == cb1){
            Toast.makeText(this,"repeat checkbox is " + isChecked, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"vibrate checkbox is " + isChecked , Toast.LENGTH_SHORT).show();
        }
    }

    float initX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            initX=  event.getRawX();
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            float diffX = initX - event.getRawX();
            if(diffX > 30)
                Toast.makeText(this, "오른쪽", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "왼쪽", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "뒤로가기", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}