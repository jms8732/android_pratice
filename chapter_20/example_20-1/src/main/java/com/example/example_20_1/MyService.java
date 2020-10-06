package com.example.example_20_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    MediaPlayer player = null;
    int duration, current;

    public void play() {
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
        }
        player = MediaPlayer.create(getApplicationContext(), R.raw.ma9ma9);

        duration = player.getDuration();
        player.start();
    }

    public void stop() {
            player.stop();
            player.release();
            player = null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }

    }


}
