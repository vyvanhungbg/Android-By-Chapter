package com.example.chapter_40_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //https://androidcoban.com/su-dung-sharedpreference-trong-android.html

    // flag trong sharedPref
//    MODE_PRIVATE: chỉ ứng dụng có thể truy nhập vào
//    MODE_WORLD_READABLE: các ứng dụng khác có thể đọc được nội dung nhưng không thể sửa hoặc thêm
//    MODE_WORLD_WRITABLE: Ứng dụng khác có thể đọc/ghi nội dung
//    MODE_MULTI_PROCESS: Nhiều process có thể cùng chỉnh sửa SharedPreference
    final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Dùng PreferencesScreen để tạo màn hình cài đặt
        // Từ Api 29 PreferencesScreen bi deprecated -> sử dụng androix.preference.PreferencesScreen
        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });

        // Setdefault setting thông qua file xml định nghia sẵn
        Button defaultSetting = findViewById(R.id.set_default);
        defaultSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                sharedPreferences.edit().clear().commit();
                PreferenceManager.setDefaultValues(MainActivity.this,R.xml.preferences,true);
            }
        });

        Button showSetting = findViewById(R.id.show_setting);
        showSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                boolean silentMode = sharedPreferences.getBoolean("silent_mode",false);
                boolean darkMode = sharedPreferences.getBoolean("awesome_mode",false);
                String path = sharedPreferences.getString("custom_storage","default");
                String options = sharedPreferences.getString("list_preferences","default");
                String ringtone = sharedPreferences.getString("ringtone","default");
                Log.e("Silent ",silentMode+"");
                Log.e("darkMode ",darkMode+"");
                Log.e("path ",path+"");
                Log.e("options ",options+"");
                Log.e("ringtone ",ringtone+"");

            }
        });

        // Apply và commit
        // apply được thêm vao 2.3 (api9) nó thực hiện mà không trả về thành công hay thất bại
        // commit trả về true nếu lưu thành công , còn lại là sai
        // apply là không đồng bộ , commit là đồng bộ
        // apply nhanh hơn , commit chậm hơn

        Button SaveInputString = findViewById(R.id.save_input_string);
        Button showAll = findViewById(R.id.show_all);
        EditText inputString = findViewById(R.id.input_string);
        Button containKey = findViewById(R.id.contain);
        Button removeKey = findViewById(R.id.remove);
        Button clearAll = findViewById(R.id.clear);

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this);

        SaveInputString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = inputString.getText().toString();
                Log.e("Str :",str);
                sharedPreferencesManager.setString(SharedPreferencesManager.KEY_STRING,str);
            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesManager.logSharedPreferences();
            }
        });

        removeKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesManager.removeKey(SharedPreferencesManager.KEY_STRING);
                Log.e(TAG, " remove key "+SharedPreferencesManager.KEY_STRING);
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesManager.clearPref();
                Log.e(TAG, " clear key ");
            }
        });

        containKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG+" string contain : ",sharedPreferencesManager.containKey(SharedPreferencesManager.KEY_STRING)+"");
            }
        });



        // chú ý biến   onSharedPreferenceChangeListener phải được đặt ngoài class và được lưu vào một biến
        // Nếu không sẽ chỉ lắng nghe được một lần và bị trình dọn rác thu gom mất do đó mất lắng nghe
        // nếu ko muốn lắng nghe thì phải unregister
        // Sẽ chỉ thay đổi nếu được thêm or thay đổi giá trị .
        sharedPreferencesManager.registerPrefsListener(onSharedPreferenceChangeListener);

    }
    private  final  SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            Log.e(TAG, "Thay đổi "+s);
        }
    };
}