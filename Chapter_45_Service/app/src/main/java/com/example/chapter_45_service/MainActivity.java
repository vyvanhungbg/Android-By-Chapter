package com.example.chapter_45_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_PUT = "KEY_PUT";
    EditText editText;
    Button btnStart, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edt);
        btnStart = findViewById(R.id.btn_start_service);
        btnStop = findViewById(R.id.btn_stop_service);
        
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