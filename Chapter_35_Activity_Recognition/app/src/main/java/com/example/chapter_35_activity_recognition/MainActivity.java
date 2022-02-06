package com.example.chapter_35_activity_recognition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient apiClient;
    private LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver localActivityReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        //This just gets the activity intent from the ActivityReceiver class
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localActivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ActivityRecognitionResult recognitionResult =
                        ActivityRecognitionResult.extractResult(intent);
                TextView textView = (TextView) findViewById(R.id.text_view);

                //This is just to get the activity name. Use at your own risk.

                textView.setText((recognitionResult.getMostProbableActivity().getType()));
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Register local broadcast receiver
        localBroadcastManager.registerReceiver(localActivityReceiver, new
                IntentFilter("activity"));
        //Connect google api client
        apiClient.connect();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Unregister for activity recognition
        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(apiClient,
                PendingIntent.getBroadcast(this, 0, new Intent(this, ActivityReceiver.class),
                        PendingIntent.FLAG_UPDATE_CURRENT));
        //Disconnects api client
        apiClient.disconnect();
        //Unregister local receiver
        localBroadcastManager.unregisterReceiver(localActivityReceiver);
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Only register for activity recognition if google api client has connected
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(apiClient, 0,
                PendingIntent.getBroadcast(this, 0, new Intent(this, ActivityReceiver.class),
                        PendingIntent.FLAG_UPDATE_CURRENT));
    }
    @Override
    public void onConnectionSuspended(int i) {
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}