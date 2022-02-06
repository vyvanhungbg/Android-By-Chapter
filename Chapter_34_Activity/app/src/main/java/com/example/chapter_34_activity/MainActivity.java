package com.example.chapter_34_activity;

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
    Button buttonGoToAC2, buttonGoToInput,buttonGoToInputRegister, buttonExit;
    TextView textView;
    final static int REQUEST_CODE_OF_INPUT_AC = 113;
    final static String KEY_GET_STRING = "INPUT_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        https://vntalking.com/giai-thich-launch-mode-va-tasks-trong-android.html
//        #Launch Mode -Standard - SingleTask- SingleTop- SingleInstance
//
//        #Standard
//                // Chế độ mặc định luôn khởi tạo một activity mới  kể cả activity có đang chạy hiện tại
//        #Singgle Top
//                +Với chế độ này, Activity sẽ chỉ có duy nhất một instance trong Task và luôn ở trên đỉnh của task.
//                +Nếu Activity đang ở trên đỉnh của Task, thì một instance mới sẽ không được tạo. Thay vào đó, hàm onNewIntent() sẽ được gọi để báo cho bạn biết.
//                +Nếu Activity đó không ở trên đỉnh, thì một instance mới sẽ được tạo và đẩy lên đỉnh của task. Trong trường này, chế độ này sẽ hoạt động với chế độ standard.
//        #SingleTask
//                  Với chế độ này, đúng như tên gọi, sẽ chỉ có duy nhất một instance của Activity tồn tại trong Task.
//                  Nếu một Activity được thiết lập ở chế độ SingleTask, một Task mới sẽ được tạo mỗi khi Activity này được tạo lần đầu tiên. Do đó, activity này sẽ là luôn là instance đầu tiên trong Task.
 //       Lúc này có 2 trường hợp:
//
//                  Nếu Activity đang ở trên đỉnh của Task, một instance chắc chắn sẽ không được tạo, thay vào đó sẽ gọi hàm onNewIntent() -> giống với chế độ SingleTop
//                  Nếu Activity tồn tại trong task, nhưng không ở trên đỉnh. Lúc này, hệ thống sẽ pop toàn bộ các instance phía trên của Activity và instance của Activity sẽ ở trên đỉnh của task, đồng thời gọi hàm onNewIntent()
//         #SingleInstance
//                   chế độ, sẽ chỉ có duy nhất một instance trong một task.
//                   Với chế độ này, cách thức hoạt động giống với SingleTask ở chỗ là sẽ task mới sẽ được tạo và instance của Activity đó sẽ đặt ở root. Nhưng điểm khác duy nhất là  task mới chỉ chứa instance của activity mà không làm gì thêm cả (không gọi hàm onNewIntent()). Nếu một activity mới được chạy từ chế độ này thì nó sẽ được thực hiện trong một task riêng biệt.
//                  Vì chỉ có duy nhất một instance trong task nên:
//                  Nếu tồn tại một instance của Activity trong một task nào đó, onNewIntent() sẽ được gọi cho Activity đó
        textView = findViewById(R.id.text_view);
        buttonGoToAC2 = findViewById(R.id.button1);
        buttonGoToAC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                //finish();
                // gọi finish thì sẽ không quay về được rõ activity đã kết thúc .
                // nếu đặt noHisory trong androiManifest của activity đó là true cũng tương tự
                // cách này thường dùng cho đăng nhập or splash activity
            }
        });

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


        // Exit App
        buttonExit = findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finishAffinity();
                ExitActivity.exitApplication(getApplicationContext());
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