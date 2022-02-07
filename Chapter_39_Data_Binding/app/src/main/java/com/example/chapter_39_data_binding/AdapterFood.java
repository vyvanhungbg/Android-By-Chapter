package com.example.chapter_39_data_binding;

import android.app.Activity;
import android.content.ClipData;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter_39_data_binding.databinding.ItemFoodBinding;

import java.util.List;

public class AdapterFood extends RecyclerView.Adapter<AdapterFood.ViewHolder> {


    Activity host;
    List<Food> list;
    OnClickItem onClickItem;

    public AdapterFood(Activity host, List<Food> list, OnClickItem onClickItem) {
        this.host = host;
        this.list = list;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodBinding binding = DataBindingUtil.inflate(host.getLayoutInflater(),
                R.layout.item_food, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = list.get(position);
        holder.bindItem(food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFoodBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public ViewHolder(ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bindItem(Food food){
            binding.setFood(food);
            binding.setClick(onClickItem);
            binding.executePendingBindings();
        }
    }
}

