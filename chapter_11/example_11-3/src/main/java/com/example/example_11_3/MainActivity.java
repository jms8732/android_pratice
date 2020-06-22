package com.example.example_11_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity{
    AutoCompleteTextView autoCompleteTextView;
    Spinner spinner;
    SeekBar seekBar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        spinner = (Spinner)findViewById(R.id.spinner);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoComplete);

        String [] array = {  "한국어",
        "영어",
        "스페인어",
        "독일어",
        "중국어",
        "일본어"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,array);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        String[] array1 = getResources().getStringArray(R.array.name);
        ArrayAdapter<String> auto = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,array1);
        autoCompleteTextView.setAdapter(auto);

        myThread thread = new myThread();
        thread.start();
    }

    class myThread extends Thread{
        @Override
        public void run() {
            for(int i =0 ; i < 10 ; i++){
                SystemClock.sleep(1000);
                progressBar.incrementProgressBy(10);
            }
        }
    }
}
