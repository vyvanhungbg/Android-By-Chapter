package com.google.android.chapter_22_videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    VideoView videoView;
    com.google.android.chapter_22_videoview.VideoView videoViewCustom;
    SeekBar seekBar;
    // Chú ý path là một luồng tream video có đuôi mp4 or mkv chứ k phải là link trang web xem video
    String pathToVideo = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/Video%2FTat-nhat-lang.mp4?alt=media&token=441121db-51ca-42b9-8087-34076657d20d";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.video_view);
        seekBar = findViewById(R.id.seek_bar);

        // set truc tiep
        //videoView.setVideoPath(pathToVideo);
        //videoView.start();

        // or custom hàm
        playVideoFromUrl(pathToVideo);

        //VideoVide Custom
        videoViewCustom = findViewById(R.id.video_view_custom);
        videoViewCustom.setVideoURI(Uri.parse(pathToVideo));
        videoViewCustom.start();



    }

    public void playVideoFromUrl(String url){
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        MediaController mediaController = new MediaController(MainActivity.this);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        enableSound(15,mp);
                        //set am luong
                        //mp.setVolume(20.0f,20.0f);
                    }
                });
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Log.e(TAG,mediaPlayer.toString());
                return false;
            }
        });
    }

    public void enableSound(int sound, MediaPlayer mp){
        Float f = Float.valueOf(sound);
        mp.setVolume(f,f);
        mp.setLooping(true);
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //Max Volume 15
        audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);  //this will return current volume.

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, sound, AudioManager.FLAG_PLAY_SOUND);   //here you can set custom volume.
        seekBar.setProgress(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int volume = (int) ((i/100.0)*sound);
                Log.e(TAG, "Volum = "+volume);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}