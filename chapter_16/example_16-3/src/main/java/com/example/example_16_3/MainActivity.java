package com.example.example_16_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import javax.net.ssl.SNIHostName;

public class MainActivity extends AppCompatActivity {
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    MyThread1 thread1;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView)findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        lv1.setAdapter(adapter);

        handler = new Handler();
        thread1 = new MyThread1();
        thread1.start();
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread1.onehandler.getLooper().quit();
    }

    class MyThread1 extends Thread{
        Handler onehandler;
        @Override
        public void run() {
            Looper.prepare();
            onehandler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if(msg.what == 1){
                        list.add("odd : " + msg.arg1);
                    }else
                        list.add("even : " + msg.arg1);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            };
            Looper.loop();
        }
    }

    private class MyThread2 extends Thread{
        @Override
        public void run() {
            Random rn = new Random();
            for(int i =0 ; i < 10 ; i++){
                int n = rn.nextInt(10);
                Message message = new Message();
                if(n % 2 == 0){
                    message.what = 1;
                    message.arg1 = n;
                }else{
                    message.what =2;
                    message.arg1 = n;
                }

                thread1.onehandler.sendMessage(message);

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
