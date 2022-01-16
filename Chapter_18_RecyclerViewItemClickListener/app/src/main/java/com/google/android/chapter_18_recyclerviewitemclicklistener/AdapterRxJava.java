package com.google.android.chapter_18_recyclerviewitemclicklistener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class AdapterRxJava extends RecyclerView.Adapter<AdapterRxJava.ViewHolder>{
    List<Item> list;
    public PublishSubject<Item> itemClickStream = PublishSubject.create();

    public AdapterRxJava(List<Item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPhone;
        LinearLayout linearLayout;
        View view;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tv_name);
            tvPhone = view.findViewById(R.id.tv_phone);
            linearLayout = view.findViewById(R.id.layout_item);

        }
        public void bindView(int position) {
            Item item = list.get(position);
            tvName.setText(item.getName());
            tvPhone.setText(item.getPhone());
            view.setOnClickListener(view1 -> itemClickStream.onNext(item));
        }

    }
}
