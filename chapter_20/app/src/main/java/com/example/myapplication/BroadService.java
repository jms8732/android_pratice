package com.example.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BroadService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer player;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");

            if(mode.equals("play")){
                try{
                    if(player != null && player.isPlaying()){
                        player.stop();
                        player.release();
                        player = null;
                    }

                    player = MediaPlayer.create(getApplicationContext(),R.raw.ma9ma9);
                    //player.prepare();
                    player.start();

                    Intent aIntent = new Intent("com.example.PLAY_TO_ACTIVITY");
                    aIntent.putExtra("mode","start");
                    aIntent.putExtra("duration",player.getDuration());

                    sendBroadcast(aIntent);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(mode.equals("stop")){
                if(player != null && player.isPlaying()){
                    player.stop();
                    player.release();
                    player = null;
                }
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(player != null){
            Intent aIntent = new Intent("com.example.PLAY_TO_ACTIVITY");
            aIntent.putExtra("mode","restart");
            aIntent.putExtra("duration",player.getDuration());
            aIntent.putExtra("current",player.getCurrentPosition());
            sendBroadcast(aIntent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver,new IntentFilter("com.example.PLAY_TO_SERVICE"));
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent("com.example.PLAY_TO_ACTIVITY");
        intent.putExtra("mode","stop");
        sendBroadcast(intent);

        stopSelf();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
