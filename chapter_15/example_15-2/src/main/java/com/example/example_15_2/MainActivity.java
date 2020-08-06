package com.example.example_15_2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn1);
        btn.setOnClickListener(this);
        edt = (EditText)findViewById(R.id.edt1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.N){
            if(isInMultiWindowMode()){
                Toast.makeText(this,"onResume....isinMultiWindowMode....", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v== btn){
            InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            edt.requestFocus();

            manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }
    }
}
