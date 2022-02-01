package com.google.android.chapter_10_paginationinrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderKt;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.chapter_10_paginationinrecyclerview.databinding.ActivityMainBinding;
import com.google.android.chapter_10_paginationinrecyclerview.my_interface.OnLoadMoreListener;
import com.google.android.chapter_10_paginationinrecyclerview.network.ApiClient;
import com.google.android.chapter_10_paginationinrecyclerview.network.FoodService;
import com.google.android.chapter_10_paginationinrecyclerview.network.ResponseObject;
import com.google.android.chapter_10_paginationinrecyclerview.viewmodel.FoodsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = AppCompatActivity.class.getSimpleName();
    String url1 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2Fimage_vit_quay_item_basic_compressed.jpeg?alt=media&token=36cf312b-d8e7-4eef-81b2-35e3c72e93d2";


    ActivityMainBinding binding;
    private FoodsViewModel foodsViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private DataAdapter adapter;


    private List<Food> foodList;
    private List<Food> tmpCheck;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recyclerView.setHasFixedSize(true);

       /* tmpCheck = new ArrayList<>();
        tmpCheck.add(new Food(1,"1",url1,"1"));
        tmpCheck.add(new Food(1,"1",url1,"1"));
        tmpCheck.add(new Food(1,"1",url1,"1"));
        tmpCheck.add(new Food(1,"1",url1,"1"));
        tmpCheck.add(new Food(1,"1",url1,"1"));
        tmpCheck.add(new Food(1,"1",url1,"1"));
        tmpCheck.add(new Food(1,"1",url1,"1"));
        foodList = new ArrayList<>();*/

        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        binding.recyclerView.setLayoutManager(layoutManager);

        foodsViewModel = new ViewModelProvider(this).get(FoodsViewModel.class);
        adapter = new DataAdapter();
        foodsViewModel.getFoodPageList().observe(this, new Observer<PagedList<Food>>() {
            @Override
            public void onChanged(PagedList<Food> foods) {
                adapter.submitList(foods);
                Log.e("Activity ","Load  "+foods.size());
            }
        });
        binding.recyclerView.setAdapter(adapter);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
            }
        });




    }



}