package com.example.chapter_39_data_binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Food {
    int url;
    String name;
    String price;

    public Food(int url, String name, String price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    @BindingAdapter("load_food_image")
    public static void loadImage(ImageView imageView, int url){  /// phải đề là static không thì sẽ bị null referen binding
        imageView.setImageDrawable(imageView.getContext().getDrawable(url));
    }


    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
