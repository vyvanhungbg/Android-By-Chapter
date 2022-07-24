package com.google.android.chapter_48_fileiowithandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tvName1,tvName2,tvName3,tvName4,tvName5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName1 = findViewById(R.id.tv_name_1);
        tvName2 = findViewById(R.id.tv_name_2);
        tvName3 = findViewById(R.id.tv_name_3);
        tvName4 = findViewById(R.id.tv_name_4);
        tvName5 = findViewById(R.id.tv_name_5);
        //Lấy thư mục làm việc hiện tại getFilesDir()

       // File file = new File(Environment.getExternalStorageDirectory(),"MyFolder");

        File myFolder = getFilesDir();
        File myFile = new File(myFolder,"myData.bin");

        //ghi file
        File mFile = new File(getFilesDir(), "myData.bin");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(myFile);
            out.write(new byte [] { 1, 2, 3, 4});
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Write four bytes one two three four:



                tvName1.setText("Folder : "+mFile.getPath());
       // tvName1.setOnClickListener(view -> openFolder(file));
    }

    public void openFolder(File file){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(Uri.fromFile(file),"*/*");
        startActivity(intent);
    }
}