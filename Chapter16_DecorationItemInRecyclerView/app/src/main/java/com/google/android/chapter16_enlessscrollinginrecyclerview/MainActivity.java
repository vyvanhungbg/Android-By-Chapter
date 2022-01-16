package com.google.android.chapter16_enlessscrollinginrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.chapter16_enlessscrollinginrecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IClickItemListener{

    ActivityMainBinding binding;
    AdapterItem adapterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recycler.setHasFixedSize(true); // tăng hiệu suất nếu các item cùng kích thước
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false); // rever đảo ngược layout
        binding.recycler.setLayoutManager(linearLayoutManager);

        List<Item> list = new ArrayList<>();
        for (int i =0;i<12;i++)
            list.add(new Item(i, i+"", i+""));

        //DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        //binding.recycler.addItemDecoration(decoration);// set vien binh thuong

        // set vien drawable custom
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(drawable);
        binding.recycler.addItemDecoration(itemDecoration);
        adapterItem = new AdapterItem(list, this) ;
        binding.recycler.setAdapter(adapterItem);


    }

    @Override
    public void ClickItem(Item item) {
        adapterItem.notifyDataSetChanged();
        Log.e("Click", "List");
    }

    @Override
    public void onLoadSuccess() {
        adapterItem.notifyDataSetChanged();
        Log.e("Load", "sucess");
    }
}