package com.google.android.sampleservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.security.Provider;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyService extends Service {
    String TAG = "SERVICE";
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"OnBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"OnCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"Start Command");
        startMusic();
        //createNotifi();
        return super.onStartCommand(intent, flags, startId);
        // gọi khi startService

    }

    private void startMusic() {

        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.yume_to_hazakura);
        }
        mediaPlayer.start();
        Log.e(TAG, "media play");
    }

    private void createNotifi(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentText("Đang chạy")
                .setContentTitle("Hello")
                .setContentIntent(pendingIntent)
                .build();
        startForeground((int) new Date().getTime(), notification);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"Destroy");
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer = null;
    }
}
