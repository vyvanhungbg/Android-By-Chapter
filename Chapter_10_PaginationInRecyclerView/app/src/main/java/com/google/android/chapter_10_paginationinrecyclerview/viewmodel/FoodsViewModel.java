package com.google.android.chapter_10_paginationinrecyclerview.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.google.android.chapter_10_paginationinrecyclerview.Food;
import com.google.android.chapter_10_paginationinrecyclerview.FoodDataSource;
import com.google.android.chapter_10_paginationinrecyclerview.FoodDataSourceFactory;

public class FoodsViewModel extends ViewModel {
    private LiveData<PagedList<Food>> foodPageList;
    private LiveData<PageKeyedDataSource<Long,Food>> liveDataSource;

    public FoodsViewModel() {
        FoodDataSourceFactory dataSourceFactory = new FoodDataSourceFactory();
        liveDataSource = dataSourceFactory.getFoodLiveData();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(FoodDataSource.POSTER_PER_PAGE)
                .build();
        foodPageList = new LivePagedListBuilder(dataSourceFactory,config).build();
        }

    public LiveData<PagedList<Food>> getFoodPageList() {
        return foodPageList;
    }
}
