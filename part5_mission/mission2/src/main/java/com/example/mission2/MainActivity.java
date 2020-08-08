package com.example.mission2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.nio.channels.InterruptedByTimeoutException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView call, location, share, internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        call = (ImageView)findViewById(R.id.call);
        location = (ImageView)findViewById(R.id.location);
        share = (ImageView)findViewById(R.id.share);
        internet = (ImageView)findViewById(R.id.web);

        call.setOnClickListener(this);
        location.setOnClickListener(this);
        share.setOnClickListener(this);
        internet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(call == v){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                request_call();
            }else
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},20);

        }else if(location == v){
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:37.5662952,126.9779451?q=37.5662952,126.9779451"));
            startActivity(intent);
        }else if(share == v){

        }else{
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://gersang.co.kr"));
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 20 && resultCode == RESULT_OK){
            request_call();
        }

    }


    private void request_call(){
        Intent intent= new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
        startActivity(intent);
    }
}
