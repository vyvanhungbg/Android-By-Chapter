package com.google.android.chapter_10_paginationinrecyclerview;

import java.util.Objects;

public class Food {
    private long idFood;
    private String foodName;
    private String foodImage;
    private String foodPrice;


    public Food() {
    }

    public Food(long idFood, String foodName, String foodImage, String foodPrice) {
        this.idFood = idFood;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
    }

    public long getIdFood() {
        return idFood;
    }

    public void setIdFood(long idFood) {
        this.idFood = idFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


}
