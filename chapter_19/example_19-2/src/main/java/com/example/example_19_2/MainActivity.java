package com.example.example_19_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button basic, bigPicture, bigText, inBox, progress, headSup;
    NotificationCompat.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basic = (Button) findViewById(R.id.basic);
        basic.setOnClickListener(this);
        bigPicture = (Button) findViewById(R.id.bigPicture);
        bigPicture.setOnClickListener(this);
        bigText = (Button) findViewById(R.id.bigText);
        bigText.setOnClickListener(this);
        inBox = (Button) findViewById(R.id.inbox);
        inBox.setOnClickListener(this);
        progress = (Button) findViewById(R.id.progress);
        progress.setOnClickListener(this);
        headSup = (Button) findViewById(R.id.headsup);
        headSup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "one-channel";
            String channelName = "My Channel one";
            String channelDescription = "Example Channel";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription(channelDescription);

            channel.enableLights(true);
            channel.setLightColor(Color.parseColor("#346152"));
            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, channelId);
        } else
            builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        builder.setContentTitle("Content Title");
        builder.setContentText("Content Message");
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.google));

        if (v == basic) {
            builder.setContentTitle("basic Notification");
            builder.setContentText("Basic");

            Intent intent = new Intent(this, this.getClass());
            PendingIntent pIntent = PendingIntent.getActivity(this, 200, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            builder.setContentIntent(pIntent);
            manager.notify(100, builder.build());
        } else if (v == bigPicture) {
            builder.setContentTitle("BigPictrue Notification");
            builder.setContentText("BigPictrue");

            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle(builder);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.google);

            bigPictureStyle.bigPicture(bitmap);
            builder.setStyle(bigPictureStyle);

            manager.notify(101, builder.build());

        } else if (v == bigText) {
            builder.setContentTitle("BigText Notification");
            builder.setContentText(
                    "나이는 갓 서른\n" +
                            "외제차를 끄는 또래에 비해서 기름 값 걱정을 덜 하는\n" +
                            "주변사람들 질투가 좀 심해서 높은 연봉에서 언급을 안하는 그는");
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);

            bigTextStyle.setSummaryText("BigTextSummary");
            bigTextStyle.setBigContentTitle("어머니의 된장국");

            builder.setStyle(bigTextStyle);

            manager.notify(102, builder.build());
        } else if (v == inBox) {
            builder.setContentTitle("Inbox Notification");
            builder.setContentText("Inbox Content");

            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(builder);
            inboxStyle.addLine("Activity");
            inboxStyle.addLine("BroadcastReceiver");
            inboxStyle.addLine("Service");
            inboxStyle.addLine("ContentProvider");
            inboxStyle.setSummaryText("Android Component");

            builder.setStyle(inboxStyle);

            manager.notify(103, builder.build());
        } else if (v == progress) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    builder.setAutoCancel(false);
                    builder.setOngoing(true);

                    for (int i = 0; i <= 10; i++) {
                        builder.setProgress(10, i, false);
                        manager.notify(104, builder.build());

                        SystemClock.sleep(1000);
                    }

                    manager.cancel(104);

                }
            });

            th.start();
        } else {

            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentTitle("Heads up Notification");
            builder.setContentText("Heads up Notification Test");
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.setContentIntent(pIntent);
            builder.setFullScreenIntent(pIntent, true);

            manager.notify(105, builder.build());
        }

    }
}
