package com.example.chapter_45_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_PUT = "KEY_PUT";
    EditText editText;
    Button btnStart, btnStop, btnExtract;

    MediaMetadataRetriever metaRetriver;

    String album_art;
    String album, artist, genre,dura, name, date;
    byte[] art;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edt);
        btnStart = findViewById(R.id.btn_start_service);
        btnStop = findViewById(R.id.btn_stop_service);
        btnExtract = findViewById(R.id.btn_extract);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickStopService();
            }
        });
        
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickStartService();
            }
        });




        btnExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = getPlayList();
                for (String item:list) {
                    extracted(item);
                }

            }
        });

    }

    private void extracted(String path) {
        metaRetriver = new MediaMetadataRetriever();
        //Log.e("Path ", String.valueOf(getResources().openRawResource(R.raw.yume_to_hazakura)));
        metaRetriver.setDataSource(path);
        try {
            art = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory
                    .decodeByteArray(art, 0, art.length);
            album_art = songImage.toString();
            album= (metaRetriver
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
            artist = (metaRetriver
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            genre = (metaRetriver
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));

            dura = (metaRetriver
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));

            name = (metaRetriver
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));



        } catch (Exception e) {
            //album_art.setBackgroundColor(Color.GRAY);
            /*album.setText("Unknown Album");
            artist.setText("Unknown Artist");
            genre.setText("Unknown Genre");*/
            Log.e("Bug", "Error " + e.getMessage());
        }finally {
            Log.e("_____", "______");
            Log.e("Infor image  : ", album_art);
            Log.e("Infor album  : ", album);
            Log.e("Infor arrits : ", artist);
            Log.e("Infor gene   : ", genre);
            Log.e("Infor dura   : ", dura);
            Log.e("Infor title  : ", name);
            Log.e("Infor date   : ", date == null ? " ": date);
            Log.e("Infor path   : ", path);
        }
    }

    List<String> getPlayList() {
        List<String> fileList = new ArrayList<String>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Log.e("URI ", uri.toString());
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
        Cursor c = getContentResolver().query(uri, projection, null,null, null);

        if (c != null) {
            c.moveToFirst();
            while (c.moveToNext()) {

               // AudioModel audioModel = new AudioModel();
                String path = c.getString(0);
                String album = c.getString(1);
                String artist = c.getString(2);

                //String name = path.substring(path.lastIndexOf("/") + 1);



                //Log.e("Name :" + name, " Album :" + album);
                //Log.e("Path :" + path, " Artist :" + artist);

                fileList.add(path);

            }
            c.close();
        }else{

        }
        return fileList;
    }

    private void clickStartService() {
        Song song = new Song("Yume To Hazakura", "Hatsune-Miku", R.drawable.ic_baseline_attach_email_24,R.raw.yume_to_hazakura);
        Intent intent = new Intent(this,MyService.class);
        //intent.putExtra(KEY_PUT,editText.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PUT,song);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void clickStopService() {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }
}