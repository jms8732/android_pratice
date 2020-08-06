package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Half;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

public class MainActivity2 extends AppCompatActivity  implements View.OnClickListener {
    ImageButton start, pause;
    TextView tv ;
    Handler handler;
    boolean run = false, finish = false;
    int time =10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        start = (ImageButton) findViewById(R.id.start);
        pause = (ImageButton)findViewById(R.id.pause);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);

        tv = (TextView)findViewById(R.id.textView);
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    tv.setText(msg.arg1);
                }else
                    tv.setText((String)msg.obj);
            }
        };
    }

    @Override
    public void onClick(View v) {
        MyThread thread = new MyThread();
        if(v == start){
            if(!run){
                run = true;
                thread.start();
            }
        }else{
            if(!run && finish){
                time = 10;
            }else if(run && !finish){
                run = false;
            }
        }
    }

    private class MyThread extends  Thread{
        @Override
        public void run() {
            while(run){
                time--;
                Message message = new Message();
                if(time == 0){
                    message.what = 2;
                    message.obj = "finish";
                    finish = true;
                    run = false;
                }else{
                    message.what =1;
                    message.arg1 = time;
                }

                handler.sendMessage(message);

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }
}