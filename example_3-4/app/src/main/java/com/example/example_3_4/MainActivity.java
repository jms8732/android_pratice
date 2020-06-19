package com.example.example_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    CheckBox cb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 =  (TextView)findViewById(R.id.tv1);
        cb1  =(CheckBox)findViewById(R.id.cb1);

        tv1.setTypeface(Typeface.MONOSPACE);

       cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   cb1.setText("is Checked");
               }else
                   cb1.setText("is UnChecked");
           }
       });
    }
}