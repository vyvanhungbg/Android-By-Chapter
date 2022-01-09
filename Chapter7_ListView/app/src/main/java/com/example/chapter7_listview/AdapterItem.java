package com.example.chapter7_listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterItem extends BaseAdapter {
    List<Item> itemList;

    public AdapterItem(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView cẩn thận list null
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        //Trả về một ID của phần
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item, viewGroup, false);

        TextView tvName,tvPhone;
        tvName = view.findViewById(R.id.tv_name);
        tvPhone = view.findViewById(R.id.tv_phone);
        Item item = itemList.get(position);

        tvName.setText(item.getName());
        tvPhone.setText(item.getPhone());
        return view;
    }
}
