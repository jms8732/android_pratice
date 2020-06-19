package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    EditText editText;
    boolean read_check, write_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        editText = (EditText) findViewById(R.id.et1);

        btn1.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            read_check = true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            write_check = true;
        }

        if (!read_check || !write_check) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                read_check = true;
            }
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                write_check = true;
            }
        }
    }

    @Override
    public void onClick(View v)  {
        if (btn1 == v) {
            boolean readable = false, writable = false;
            String state = Environment.getExternalStorageState();

            if (state.equals(Environment.MEDIA_MOUNTED)) {
                if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
                    readable = true;
                    writable = false;
                } else {
                    readable = true;
                    writable = true;
                }
            }

            if (writable) {
                try {
                    String text = editText.getText().toString();
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "myApp";


                    File dir = new File(dirPath);

                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    File file = new File(dirPath+"/file.txt");

                    if(!file.exists()) {
                        file.createNewFile();
                    }

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(text);
                    bw.flush();
                    bw.close();

                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent(this,ReadActivity.class);
            startActivity(intent);
        }
    }
}