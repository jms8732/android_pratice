package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileLockInterruptionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView plus;
    TextView camera, video;
    AlertDialog customDialog;
    File filePath;
    int reqWidth, reqHeight;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = (ImageView) findViewById(R.id.main_photo_icon);
        layout = (RelativeLayout) findViewById(R.id.main_content);

        plus.setOnClickListener(this);
        reqHeight = getResources().getDimensionPixelSize(R.dimen.reqHeight);
        reqWidth = getResources().getDimensionPixelSize(R.dimen.reqwidth);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("filePath",filePath.getAbsolutePath());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            reqWidth = getResources().getDimensionPixelSize(R.dimen.reqwidth);
            reqHeight = getResources().getDimensionPixelSize(R.dimen.reqHeight);
            Toast.makeText(this, "PorTrait", Toast.LENGTH_SHORT).show();
        }else{
            reqWidth = getResources().getDimensionPixelSize(R.dimen.porWidth);
            reqHeight = getResources().getDimensionPixelSize(R.dimen.porHeight);
            Toast.makeText(this, "LandScape", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String path = savedInstanceState.getString("filePath");
        filePath = new File(path);
        putCamera();
    }

    private Bitmap rotate(Bitmap bitmap , int angle){
        Matrix matrix=  new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    @Override
    public void onClick(View v) {
        if (v == plus) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("촬영");
                builder.setItems(new String[]{"사진 촬영", "동영상 촬영"}, myDialogInterface);
                customDialog = builder.create();
                customDialog.show();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},20);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            putCamera();
        }else if(requestCode == 20 && resultCode == RESULT_OK){
            putVideo();
        }
    }

    private void putVideo(){

            VideoView videoView = new VideoView(getApplicationContext());
            videoView.setMediaController(new MediaController(getApplicationContext()));
            Uri videoUri = Uri.parse(filePath.getAbsolutePath());

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            Bitmap bmp = null;
            retriever.setDataSource(filePath.getAbsolutePath());
            bmp = retriever.getFrameAtTime();

            int videoHeight = bmp.getHeight();
            int videoWidth = bmp.getWidth();

            videoView.setVideoURI(videoUri);

            RelativeLayout.LayoutParams params = null;
            if (videoWidth >= videoHeight) {
                params = new RelativeLayout.LayoutParams(reqWidth, reqHeight);

                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else {
                params = new RelativeLayout.LayoutParams(reqHeight, reqWidth);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }

            layout.addView(videoView, params);
            videoView.start();

    }

    private void putCamera(){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        try {
            InputStream in = new FileInputStream(filePath);
            BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int height = options.outHeight;
        final int width = options.outWidth;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = (int) Math.round((float) height / (float) reqHeight);
            final int widthRatio = (int) Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio > widthRatio ? widthRatio : heightRatio;
        }

        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inSampleSize = inSampleSize;

        Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), options1);
        bitmap = rotate(bitmap,270);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(imageView, params);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 20 && grantResults.length >0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
               customDialog.show();
            }else
                Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
        }
    }

    DialogInterface.OnClickListener myDialogInterface = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {
                //사진 촬영
                customDialog.dismiss();
                String path = getExternalFilesDir(null).getAbsolutePath() + "/myApp";
                File dir = new File(path);

                if (!dir.exists()) {
                    dir.mkdir();
                }

                try {
                    filePath = File.createTempFile("IMG", ".jpg", dir);
                    if (!filePath.exists()) {
                        filePath.createNewFile();
                    }

                    Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", filePath);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                    startActivityForResult(intent, 10);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                String path = getExternalFilesDir(null).getAbsolutePath() + "/myApp";
                File dir = new File(path);

                if (!dir.exists()) {
                    dir.mkdir();
                }

                try {
                    filePath = File.createTempFile("VIDEO", ".mp4", dir);
                    if (!filePath.exists()) {
                        filePath.createNewFile();
                    }

                    Uri videoURI = FileProvider.getUriForFile(getApplicationContext(),BuildConfig.APPLICATION_ID +".provider",filePath);

                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,videoURI);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024 * 10);

                    startActivityForResult(intent, 20);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    };
}
