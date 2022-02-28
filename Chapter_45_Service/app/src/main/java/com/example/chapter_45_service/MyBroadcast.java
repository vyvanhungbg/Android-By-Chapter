package com.example.chapter_45_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcast extends BroadcastReceiver {

    private static final String KEY_FLAG = "FLAG";
    private static final String KEY_PUT_BROADCAST_TO_SERVICE = "BOARD_SERVICE";

    @Override
    public void onReceive(Context context, Intent intent) {
            int actionFlag = intent.getIntExtra(KEY_FLAG,0);

            Intent intentToService = new Intent(context, MyService.class);
            intentToService.putExtra(KEY_PUT_BROADCAST_TO_SERVICE,actionFlag);
            context.startService(intentToService);
    }
}
