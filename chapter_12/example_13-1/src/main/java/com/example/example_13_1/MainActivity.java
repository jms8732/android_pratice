package com.example.example_13_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements onMyListener{
    PlusMinusButton pmb;
    View line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pmb = (PlusMinusButton)findViewById(R.id.plus);
        pmb.setListeners(this);
        line = (View)findViewById(R.id.line);
    }

    @Override
    public void onChange(int value) {
        int color;
        if(value < 0){
            //빨간색
            color = Color.RED;
        }else if( value >= 0 && value <= 30){
            //노란색
            color = Color.YELLOW;
        }else if(value > 30 && value <= 60){
            //파란색
            color = Color.BLUE;
        }else{
            //초록색
            color = Color.GREEN;
        }

        line.setBackgroundColor(color);
    }

}
