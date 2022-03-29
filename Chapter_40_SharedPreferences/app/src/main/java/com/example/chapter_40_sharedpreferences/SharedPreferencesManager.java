package com.example.chapter_40_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

public class SharedPreferencesManager {
    private static final String PREFS_FILE = "NameOfYourPreferenceFile";
    // PREFS_MODE defines which apps can access the file
    private static final int PREFS_MODE = Context.MODE_PRIVATE;
    // you can use live template "key" for quickly creating keys
    public static final String KEY_BOOLEAN = "KEY_FOR_YOUR_BOOLEAN";
    public static final String KEY_STRING = "KEY_FOR_YOUR_STRING";
    public static final String KEY_FLOAT = "KEY_FOR_YOUR_FLOAT";
    public static final String KEY_INT = "KEY_FOR_YOUR_INT";
    private static final String KEY_LONG = "KEY_FOR_YOUR_LONG";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_FILE,PREFS_MODE);
    }

    public int getInt(String key){
        return sharedPreferences.getInt(key,0); // 0 là giá trị trả về mặc định
    }

    public String getString(String key){
        return sharedPreferences.getString(key,"no value");
    }

    public void setString(String key, String value){
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void logSharedPreferences(){
        Map<String,?> allEntries = sharedPreferences.getAll();
        for(Map.Entry<String, ?> entry : allEntries.entrySet()){
            Log.e(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    public void clearPref(){
        sharedPreferences.edit().clear().commit();
    }
    public void removeKey(String key){
        sharedPreferences.edit().remove(key).apply();
    }

    public boolean containKey(String key){
        return sharedPreferences.contains(key);
    }


    /// Lưu object vào sharedprefences
    private static String createJSONStringFromObject(Object object){
        Gson gson = new Gson();
        return gson.toJson(object).toString();
    }

//    public <M> void setObject(String key, M modelObject) {
//        String value = createJSONStringFromObject(modelObject);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(key, value);
//        editor.apply();
//    }
//
//    public <M > M getObject(String key, Class<M> classOfModelObject) {
//        String jsonData = sharedPreferences.getString(key, null);
//        if (null != jsonData) {
//            try {
//                Gson gson = new Gson();
//                M customObject = gson.fromJson(jsonData, classOfModelObject);
//                return customObject;
//            } catch (ClassCastException cce) {
//                Log.d("Class Pref :", "Cannot convert string obtained from prefs into collection of type " +
//                        classOfModelObject.getName() + "\n" + cce.getMessage());
//            }
//        }
//        return null;
//    }

    public void registerPrefsListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }
    public void unregisterPrefsListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
