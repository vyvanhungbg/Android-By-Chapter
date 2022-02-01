package com.google.android.chapter_10_paginationinrecyclerview.network;

import com.google.android.chapter_10_paginationinrecyclerview.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {
    @GET("food")
    Call<ResponseObject<List<Food>>> getAllFoodQuery(@Query("limitStart") long limitStart, @Query("limitEnd") long limitEnd);

    @GET("food")
    Call<ResponseObject<List<Food>>> getAllFood();
}
