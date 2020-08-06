package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton start, pause;
    TextView tv;
    int time = 10;
    Handler handler;
    boolean s = false, f = false, run = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (ImageButton) findViewById(R.id.start);
        pause = (ImageButton) findViewById(R.id.pause);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.textView);
        handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        MyThread r = new MyThread();

        Thread thread = new Thread(r);
        if (start == v) {
            if(!run) {
                run = true;
                thread.start();
            }
        } else {
            if(run){
                run = false;
            }
        }
    }

    private class runnable implements Runnable {
        private String text;

        public runnable(String content) {
            this.text = content;
        }


        @Override
        public void run() {
            tv.setText(this.text);
        }
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            while(run){
                time--;
                if(time == 0){
                    handler.post(new runnable("Finish!!"));
                    run =false;
                }else{
                    handler.post(new runnable(String.valueOf(time)));
                }

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}