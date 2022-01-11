package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAutoImageSlide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auto_image_slide);

        List<Integer> images = new ArrayList<>();
        images.add(R.color.white);
        images.add(R.color.purple_200);
        images.add(R.color.teal_200);
        SliderView imageSlider = findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(images, this);
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        imageSlider.setScrollTimeInMillis(2800);
        imageSlider.startAutoCycle();
    }
}