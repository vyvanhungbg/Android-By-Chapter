package com.example.chapter10_textinputlayput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputLayout = findViewById(R.id.layout_pass);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = textInputLayout.getEditText().getText().toString().trim();  // getText ko sẽ ko lấy đc text
                Log.e("Pass", pass);
                if(pass.length() > 12){
                    textInputLayout.getEditText().setError("Độ dài vượt quá 12 kí tự");
                }

            }
        });
    }
}