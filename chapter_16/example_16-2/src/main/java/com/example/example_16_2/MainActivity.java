package com.example.example_16_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton start ,pause;
    int time = 10;
    TextView tv;

    boolean run = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (ImageButton)findViewById(R.id.start);
        pause = (ImageButton)findViewById(R.id.pause);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);

        tv = (TextView)findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        if(v == start){
            if(!run){
                run = true;
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }
        }else{
            if(run){
                run = false;
            }
        }
    }


    private class MyAsyncTask extends AsyncTask<Void,Integer,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tv.setText(String.valueOf(values[0]));
        }

        @Override
        protected String doInBackground(Void... voids) {
            while(run){
                publishProgress(--time);

                if(time == 0) {
                    run = false;
                    return "finish!!";
                }

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            return String.valueOf(time);
        }
    }
}
