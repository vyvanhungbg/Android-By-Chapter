package com.example.chapter_41_intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final static int REQUEST_CODE_OF_INPUT_AC = 113;
    final static String KEY_GET_STRING = "INPUT_TEXT";

    Button buttonGoToAC2, buttonGoToInput,buttonGoToInputRegister, buttonExit;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // start activity result đã bị decrepted
        buttonGoToInput = findViewById(R.id.button_go_to_input);
        buttonGoToInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(intent,REQUEST_CODE_OF_INPUT_AC);
            }
        });

        // cách mới
        ActivityResultLauncher<Intent> myActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        String string = "";
                        if(result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            if(data!=null){
                                string = data.getStringExtra(KEY_GET_STRING);
                            }

                        }else if(result.getResultCode() == RESULT_CANCELED) {
                            string = "Cancel";
                        }
                        textView.setText(string + " from registerForAC");
                    }
                }
        );

        buttonGoToInputRegister = findViewById(R.id.button_go_to_input_register);
        buttonGoToInputRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                myActivity.launch(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_CODE_OF_INPUT_AC){
            String string = "";
            if(resultCode == RESULT_OK && data!=null){
                string = data.getStringExtra(KEY_GET_STRING);
            }else if(resultCode == RESULT_CANCELED){
                string = "Cancel";
            }else {
                string = "null !";
            }
            textView.setText(string + " from startAcForResult");
        }
    }
}