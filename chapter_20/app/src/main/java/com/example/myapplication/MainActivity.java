package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton play, stop;
    ProgressBar progressBar;
    boolean runThread = false;


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");

            if (mode.equals("start")) {
                int duration = intent.getIntExtra("duration", 0);
                progressBar.setMax(duration);
                progressBar.setProgress(0);
            } else if (mode.equals("restart")) {
                int duration = intent.getIntExtra("duration",0);
                int current = intent.getIntExtra("current",0);
                progressBar.setMax(duration);
                progressBar.setProgress(current);
                runThread= true;

                PlayThread thread = new PlayThread();
                thread.start();

            } else if (mode.equals("stop")) {
                runThread = false;
            }
        }
    };


    class PlayThread extends Thread {
        @Override
        public void run() {
            while (runThread) {
                progressBar.incrementProgressBy(1000);
                SystemClock.sleep(1000);

                if(progressBar.getProgress() == progressBar.getMax())
                    runThread = false;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(receiver, new IntentFilter("com.example.PLAY_TO_ACTIVITY"));

        play = (ImageButton) findViewById(R.id.play);
        play.setOnClickListener(this);
        stop = (ImageButton) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);


        Intent intent = new Intent(this,BroadService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == play) {
            Intent intent = new Intent("com.example.PLAY_TO_SERVICE");
            intent.putExtra("mode", "play");
            runThread= true;

            PlayThread thread = new PlayThread();
            thread.start();

            sendBroadcast(intent);
        } else {
            Intent intent = new Intent("com.example.PLAY_TO_SERVICE");
            intent.putExtra("mode", "stop");
            sendBroadcast(intent);
        }
    }
}