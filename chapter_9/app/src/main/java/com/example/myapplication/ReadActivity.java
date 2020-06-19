package com.example.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadActivity extends AppCompatActivity {
    static TextView tv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv1 = (TextView)findViewById(R.id.tv1);

        read();
    }

    private static void read(){
        String state= Environment.getExternalStorageState();

        if(state.equals(Environment.MEDIA_MOUNTED)){
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "myApp";
            String file =  dirPath + "file1.txt";
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuffer sb = new StringBuffer();
                String tmp = null;
                while((tmp = br.readLine()) != null){
                    sb.append(tmp);
                }

                br.close();
                tv1.setText(sb.toString());

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
