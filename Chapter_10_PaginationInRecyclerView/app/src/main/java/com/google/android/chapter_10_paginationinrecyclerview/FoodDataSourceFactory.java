package com.google.android.chapter_10_paginationinrecyclerview;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class FoodDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Long,Food>> foodLiveData
            = new MutableLiveData<>();
    @Override
    public DataSource create() {
        FoodDataSource foodDataSource = new FoodDataSource();
        foodLiveData.postValue(foodDataSource);
        return foodDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Long, Food>> getFoodLiveData() {
        return foodLiveData;
    }
}
