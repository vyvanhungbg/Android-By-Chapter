package com.example.textview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);
        textView6 = findViewById(R.id.text_view6);

        // Thẻ span
        String firstWord = "Họ và tên : ";
        String lastWord = "Vy Văn Hùng";
        int purple = getResources().getColor(R.color.purple_200);
        int teal = getResources().getColor(R.color.teal_200);

        Spannable spannable = new SpannableString(firstWord+lastWord);
        RelativeSizeSpan sizeSpanOfFirstWord = new RelativeSizeSpan(1.9f);
        RelativeSizeSpan sizeSpanOfLastWord = new RelativeSizeSpan(0.8f);

        spannable.setSpan(sizeSpanOfFirstWord, 0 , firstWord.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(purple), 0 , firstWord.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(sizeSpanOfLastWord, 0 , firstWord.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(teal), 0 , firstWord.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            spannable.setSpan(new TypefaceSpan(Typeface.SANS_SERIF), 0 , firstWord.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView1.setText(spannable);

        // Gạch ngang từng chữ văn bản
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(firstWord+lastWord);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        stringBuilder.setSpan(strikethroughSpan,0,firstWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(stringBuilder);

        // Gạch ngang toàn bộ văn bản
        // Chưa set được màu gach ngang
        String strikethroughText = "Gạch ngang" ;
        Paint paint = new Paint();
        textView2.setPaintFlags(textView2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        textView2.setText(strikethroughText);

        // ảnh vs text view
        Drawable leftDrawable = getResources().getDrawable( R.drawable.ic_launcher_background);
        leftDrawable.setBounds(0,0,20,20); // set kích thước cho ảnh . Nếu không có sẽ không hiện
        textView4.setCompoundDrawables(leftDrawable, null, null , null); // set ảnh xung quanh text view null sẽ ko có

        // số mũ , superscriptSpan

        SpannableString superscriptString = new SpannableString("RM19:20");
       // superscriptString.setSpan(new SuperscriptSpan(),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        superscriptString.setSpan(new TopAlignSuperscriptSpan(0.35f),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView5.setText(superscriptString);

        // Sử dụng đến html để hiển thị
       textView6.setText(Html.fromHtml("<p>Testing <sub>subscript text</sub></p>"));

       //Custom
        CustomTextView customTextView = (CustomTextView) findViewById(R.id.custom);
        customTextView.setStroke(1,purple,Paint.Join.MITER,1.0f);
        customTextView.setText("Custom");

    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }



}