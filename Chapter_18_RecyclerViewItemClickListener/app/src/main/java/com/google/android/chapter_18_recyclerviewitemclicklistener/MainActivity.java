package com.google.android.chapter_18_recyclerviewitemclicklistener;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.chapter_18_recyclerviewitemclicklistener.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IClickItemListener {

    ActivityMainBinding binding;
    AdapterItem adapterItem;
    AdapterRxJava adapterRxJava;
    AdapterItemNormal adapterItemNormal;
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




       /* adapterItem = new AdapterItem(list, this) ;  // Cách 1 click bằng interface
        binding.recycler.setAdapter(adapterItem);*/



       /* adapterRxJava = new AdapterRxJava(list);   // Cách 2 Click theo cách 2 dùng thư viên RxJava Quản lí Luồng
        adapterRxJava.itemClickStream.subscribe(item -> {
            Toast.makeText(this,"Rx "+ item.getName(),Toast.LENGTH_LONG).show();
        });
        binding.recycler.setAdapter(adapterRxJava);*/

        adapterItemNormal = new AdapterItemNormal(list); // Cách 3
        binding.recycler.setAdapter(adapterItemNormal);
        binding.recycler.addOnItemTouchListener(new RecyclerTouchListener(this, binding.recycler, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {
                Toast.makeText(child.getContext(),"Adapter Normal Long Click "+ list.get(childPosition).getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClick(View child, int childPosition) {
                Toast.makeText(child.getContext(),"Adapter Normal  "+ list.get(childPosition).getName(),Toast.LENGTH_LONG).show();
            }
        }));


    }

    @Override
    public void ClickItem(Item item) {
        Toast.makeText(this, item.getName(),Toast.LENGTH_LONG).show();
    }

}