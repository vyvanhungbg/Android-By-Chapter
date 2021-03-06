package com.example.recyclerviewadvanced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerviewadvanced.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IClickItemListener{

    ActivityMainBinding binding;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recycler.setHasFixedSize(true); // tăng hiệu suất nếu các item cùng kích thước
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false); // rever đảo ngược layout
        binding.recycler.setLayoutManager(linearLayoutManager);

        List<Item> list = new ArrayList<>();
       for ( i =0;i<12;i++)
           list.add(new Item(i, i+"", i+""));

        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        binding.recycler.addItemDecoration(decoration);// set vien


       // AdapterItem adapterItem = new AdapterItem(list, this); dùng adapter thường
       AdapterItemBinding adapterItem = new AdapterItemBinding(list,this);// dùng adapter databinding
        //AdapterItem adapterItem = new AdapterItem(new ArrayList<>(),this);// dùng adapter  nhưng list rỗng check EmptyView


        //MyAdapter myAdapter = new MyAdapter(this);// dùng adapter sortlist
        binding.recycler.setAdapter(adapterItem);
        /*binding.recycler.setAdapter(myAdapter);  // các nút bấm của Adapter dùng sortlist


        binding.addAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.addModels(list);
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.addModel(new Item(i, i+"", i+""));
                i++;
            }
        });

        binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.removeModelAt(0);
            }
        });

        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.clear();
            }
        });

        binding.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.addModel(new Item(5, "Hung", 5+""));
            }
        });*/

        ///
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |  ItemTouchHelper.UP | ItemTouchHelper.DOWN) {


            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;  // định hướng vuốt và kéo ryclerview
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }// Phần này là để xác định hướng vuốt or kéo . Nếu không sẽ chỉ nhận ra swpie

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                final int fromPosition = viewHolder.getAdapterPosition();
                final int toPosition = target.getAdapterPosition();

                Collections.swap(list, fromPosition, toPosition);
                adapterItem.notifyItemMoved(fromPosition, toPosition);

                Log.e("On Move", viewHolder.getAdapterPosition()+"");
                return true; // true là đã chuyển , false là khác
            }

            @Override
            public boolean isLongPressDragEnabled() { /// có phần gim item lâu này thì mới nhận ra kéo và thực hiện đc
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int pos = viewHolder.getAdapterPosition();
                list.remove(pos);
                adapterItem.notifyItemRemoved(pos);
                Log.e("On_Swiped", viewHolder.getAdapterPosition()+"");
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.recycler); // gắn vào recycler view
        // tìm hiểu thêm ItemTouchHelper.startDrag...
    }

    @Override
    public void ClickItem(Item item) {
        Toast.makeText(this,"Id :"+item.getId()+"\n ten : " +item.getName(),Toast.LENGTH_LONG).show();
    }
}