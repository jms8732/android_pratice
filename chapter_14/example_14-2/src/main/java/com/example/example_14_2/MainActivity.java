package com.example.example_14_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn[];
    ImageView iv1;
    File filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = new Button[7];
        int btn_id[] = {R.id.address, R.id.camera1, R.id.camera2, R.id.speech, R.id.map, R.id.browser, R.id.call};

        for (int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setOnClickListener(this);
        }

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        int requestCode = -1;
        if (v == btn[0]) {
            //주소록 앱 연동
            intent = new Intent(Intent.ACTION_PICK);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
            requestCode = 100;

            startActivityForResult(intent, requestCode);
        } else if (v == btn[1]) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            requestCode = 101;

            startActivityForResult(intent, requestCode);
        } else if (v == btn[2]) {
            requestCode = 102;

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                String path = getExternalFilesDir(null).getAbsolutePath() + "/myApp";
                File dir = new File(path);

                //디렉토리가 없을 경우, 디렉토리 생성
                if (!dir.exists()) {
                    if (dir.mkdir())
                        System.out.println("Create Dir");
                }
                try {
                    //파일 생성
                    filePath = File.createTempFile("IMG", ".jpg", dir);
                    if (!filePath.exists()) {
                        filePath.createNewFile();
                    }

                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", filePath);

                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                startActivityForResult(intent, requestCode);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        } else if (v == btn[3]) {
            requestCode = 103;
            intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성 인식 테스트");
            startActivityForResult(intent, requestCode);
        } else if (v == btn[4]) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952,126.9779451"));
            startActivity(intent);
        } else if (v == btn[5]) {
            intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.gersang.co.kr"));
            startActivity(intent);
        } else if (v == btn[6]) {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-102"));
                startActivity(intent);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},25);
            }
        } else if (v == iv1) {

            intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", filePath);
            intent.setDataAndType(photoURI, "image/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            String name = data.getDataString();
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            iv1.setImageBitmap(bitmap);
        } else if (requestCode == 102 && resultCode == RESULT_OK) {
            BitmapFactory.Options imgOptions = new BitmapFactory.Options();
            imgOptions.inJustDecodeBounds = true;

            try {
                InputStream in = new FileInputStream(filePath);
                BitmapFactory.decodeStream(in, null, imgOptions);
                in.close();
                in = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            final int height = imgOptions.outHeight;
            final int width = imgOptions.outWidth;

            int inSampleSize = 1;
            int req_height = getResources().getDimensionPixelSize(R.dimen.request_image_height);
            int req_width = getResources().getDimensionPixelSize(R.dimen.request_image_width);
            if (width > req_width || height > req_height) {
                final int h_ratio = Math.round((float) height / (float) req_height);
                final int w_ratio = Math.round((float) width / (float) req_width);
                inSampleSize = h_ratio < w_ratio ? h_ratio : w_ratio;
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = inSampleSize;
            Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), options);
            iv1.setImageBitmap(bitmap);
        } else if (requestCode == 103) {
            ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String result = list.get(0);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
