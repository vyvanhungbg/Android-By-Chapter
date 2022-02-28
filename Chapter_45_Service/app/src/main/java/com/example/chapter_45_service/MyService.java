package com.example.chapter_45_service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private static final int FlAG_PAUSE = 1;
    private static final int FlAG_RESUME = 2;
    private static final int FlAG_STOP = 3;

    private static final String KEY_PUT = "KEY_PUT";
    private static final String TAG = MyService.class.getSimpleName();
    private static final String KEY_FLAG = "FLAG";

    private static final String KEY_PUT_BROADCAST_TO_SERVICE = "BOARD_SERVICE";

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    private  Song mSong;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"My service onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         Song song = intent.getParcelableExtra(KEY_PUT);
        if(song != null){
            startMusic(song);
            createNotification(song);
            mSong = song;
        }

        int actionFlag = intent.getIntExtra(KEY_PUT_BROADCAST_TO_SERVICE,0);
        handleActionMusic(actionFlag);

        return START_NOT_STICKY;

    }

    private void startMusic(Song song) {
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }
        mediaPlayer.start();
        isPlaying = true;
        Log.e(TAG, "media play");
    }

    private void handleActionMusic(int action){
        switch (action){
            case FlAG_PAUSE:
                actionPause();
                break;
            case FlAG_RESUME:
                actionResume();
                break;
            case FlAG_STOP:
                stopSelf();
                break;
        }
    }

    private void actionPause(){
        if(mediaPlayer !=null && isPlaying == true){
            mediaPlayer.pause();
            isPlaying = false;
            createNotification(mSong);
        }
    }

    private void actionResume(){
        if(mediaPlayer != null && isPlaying == false){
            mediaPlayer.start();
            isPlaying = true;
            createNotification(mSong);
        }
    }




    private void createNotification(Song song) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),song.getImage());


        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.tv_name_song,song.getTitle());
        remoteViews.setTextViewText(R.id.tv_name_single,song.getSingle());
        remoteViews.setImageViewBitmap(R.id.image_song,bitmap);
        remoteViews.setImageViewResource(R.id.tmg_pause_of_start,R.drawable.ic_baseline_pause_circle_outline_24);

        if(isPlaying){
            remoteViews.setOnClickPendingIntent(R.id.tmg_pause_of_start,getPendingIntent(this,FlAG_PAUSE));
            remoteViews.setImageViewResource(R.id.tmg_pause_of_start,R.drawable.ic_baseline_pause_circle_outline_24);
        }else {
            remoteViews.setOnClickPendingIntent(R.id.tmg_pause_of_start,getPendingIntent(this,FlAG_RESUME));
            remoteViews.setImageViewResource(R.id.tmg_pause_of_start,R.drawable.ic_baseline_not_started_24);
        }

        remoteViews.setOnClickPendingIntent(R.id.img_stop,getPendingIntent(this,FlAG_STOP));

        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setSmallIcon(song.getImage())
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setSound(null)
                .build();
        startForeground(1, notification);
    }

    private PendingIntent getPendingIntent(Context context, int flag) {
        Intent intent = new Intent(this,MyBroadcast.class);
        intent.putExtra(KEY_FLAG,flag);

        return PendingIntent.getBroadcast(context.getApplicationContext(),flag, intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.e(TAG,"My service onDestroy");
    }
}
