package com.google.android.chapter_10_paginationinrecyclerview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.google.android.chapter_10_paginationinrecyclerview.network.ApiClient;
import com.google.android.chapter_10_paginationinrecyclerview.network.FoodService;
import com.google.android.chapter_10_paginationinrecyclerview.network.ResponseObject;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodDataSource extends PageKeyedDataSource <Long,Food>{

    final static Long FIRST_PAGE = 1L;
    static  int count=1;
    public final static int POSTER_PER_PAGE = 20;
    Retrofit retrofit = ApiClient.getClient();
    FoodService foodService = retrofit.create(FoodService.class);



    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Food> callback) {
        Call<ResponseObject<List<Food>>> call = foodService.getAllFoodQuery(0,FIRST_PAGE*POSTER_PER_PAGE);
        call.enqueue(new Callback<ResponseObject<List<Food>>>() {
            @Override
            public void onResponse(Call<ResponseObject<List<Food>>> call, Response<ResponseObject<List<Food>>> response) {
                if(response.isSuccessful()){
                    List<Food> foods = response.body().getData();
                    Log.e("Data source","Load init "+foods.size() );
                    callback.onResult(foods,null,FIRST_PAGE+1);

                }
            }

            @Override
            public void onFailure(Call<ResponseObject<List<Food>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Food> callback) {
        Call<ResponseObject<List<Food>>> call = foodService.getAllFoodQuery(count*POSTER_PER_PAGE,(count+1)*POSTER_PER_PAGE);
        call.enqueue(new Callback<ResponseObject<List<Food>>>() {
            @Override
            public void onResponse(Call<ResponseObject<List<Food>>> call, Response<ResponseObject<List<Food>>> response) {
                if(response.isSuccessful()){
                    List<Food> foods = response.body().getData();
                    Log.e("Data source","Load food size  "+foods.size() );
                    Log.e("Data source","param.Key  "+params.key );
                    Log.e("Data source","Load  from"+(count*POSTER_PER_PAGE) );
                    Log.e("Data source","to Load  "+((count+1)*POSTER_PER_PAGE) );
                    callback.onResult(foods,params.key +1);
                    count++;
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<List<Food>>> call, Throwable t) {

            }
        });
    }
}
