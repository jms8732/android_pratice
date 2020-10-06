package com.example.example_20_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton play, stop;
    ProgressBar progressBar;
    MyService myService;
    boolean runThread= false;

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (ImageButton) findViewById(R.id.play);
        play.setOnClickListener(this);
        stop = (ImageButton) findViewById(R.id.stop);
        stop.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progress);


    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder temp = (MyService.MyBinder) service;
            myService = temp.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            unbindService(connection);
        }
    };

    @Override
    public void onClick(View v) {
        if (v == play) {
            if (myService != null) {
                myService.play();

                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        runThread = true;
                        progressBar.setProgress(0);
                        progressBar.setMax(myService.duration);
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        while(runThread){
                            progressBar.incrementProgressBy(1000);
                            SystemClock.sleep(1000);

                            if(progressBar.getMax() == progressBar.getProgress()){
                                runThread = false;
                            }
                        }

                        return null;
                    }
                }.execute();

            }
        } else {
            runThread = false;
            myService.stop();
        }
    }

}
