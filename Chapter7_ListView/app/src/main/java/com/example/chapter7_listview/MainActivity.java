package com.example.chapter7_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_list_view);
        List<Item> list = new ArrayList<>();
        list.add(new Item("Hùng", "0329843947"));
        list.add(new Item("Hà", "0329843947"));
        list.add(new Item("Hải", "0329843947"));
        list.add(new Item("Quỳnh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));
        list.add(new Item("Minh", "0329843947"));

        AdapterItem adapterItem = new AdapterItem(list);
        listView.setAdapter(adapterItem);
        listView.setFooterDividersEnabled(false); //Khi được đặt thành false, ListView sẽ không vẽ dải phân cách trước mỗi chế độ xem chân trang. Giá trị mặc định là true.
        listView.setHeaderDividersEnabled(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = list.get(i);
                Toast.makeText(MainActivity.this, item.getName(),Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = list.get(i);
                list.remove(item);
                adapterItem.notifyDataSetChanged();
                return true;
            }
        });

    }
}