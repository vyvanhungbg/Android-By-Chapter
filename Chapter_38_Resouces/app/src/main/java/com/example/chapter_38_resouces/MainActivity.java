package com.example.chapter_38_resouces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Colors are represented by hexadecimal color values for each color channel (0 - FF) in one of the formats:
//            #RGB
//            #ARGB
//            #RRGGBB
//            #AARRGGBB
//                Legend
//        A - alpha channel - 0 value is fully transparent, FF value is opaque
//        R - red channel
//        G - green channel
//        B - blue channel
//        Defined colors can be used in XML with following syntax @color/name_of_the_color

        // version > 1.6
        int color = ContextCompat.getColor(this,R.color.purple_200);

        // version <6.0
        int myColor = getResources().getColor(R.color.purple_700); // API >23 bị decrept
        TextView textView = findViewById(R.id.text_view);
        textView.setBackgroundColor(color);

            // Define color with anphal

        //# Define quantity in string.xml
            // plurals
                //few
                //many
                //one
                //other
                //two
                //zero
        textView.setText(getResources().getQuantityString(R.plurals.hello_people, 3, 3));

        //# Define Dimension in dimens.xml
//                You can use different units :
//                sp : Scale-independent Pixels. For fonts. // Pixel không phụ thuộc vào tỉ lệ
//                dp : Density-independent Pixels. For everything else.// Pixel không phụ thuộc vào mật độ
//                pt : Points
//                px : Pixels
//                mm : Millimeters
//                im : Inches
//                  Dimes các viewGroup view thì dp
 //                       chữ thì dùng sp

        //# Define String format in String.xml
//                    %1$s
//                    '%' separates from normal characters,
//                    '1' denotes first parameter,
//                    '$' is used as separator between parameter number and type,
//                    's' denotes string type ('d' is used for integer)

        String welcomePokemonTrainerText = getString(R.string.welcome_trainer, "Xuka", 10);
        TextView textView2 = findViewById(R.id.text_view2);
        textView2.setText(welcomePokemonTrainerText);

        //# Define Array-String in arrays.xml
        int[] integerList = getResources().getIntArray(R.array.integer_array_name);
        Log.e(TAG, Arrays.toString(integerList));

        List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.string_array_name));
        Log.e(TAG, stringList.toString());
   }
}