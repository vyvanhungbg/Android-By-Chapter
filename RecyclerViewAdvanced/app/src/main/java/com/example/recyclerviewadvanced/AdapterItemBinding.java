package com.example.recyclerviewadvanced;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewadvanced.databinding.ItemBinding;

import java.util.List;

public class AdapterItemBinding extends RecyclerView.Adapter<BindingViewHolder<ItemBinding>> {
    List<Item> list;
    IClickItemListener listener;

    public AdapterItemBinding(List<Item> list, IClickItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BindingViewHolder<ItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new BindingViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemBinding> holder, int position) {
        Item item = list.get(position);
        holder.getBinding().tvName.setText(item.getName());
        holder.getBinding().tvPhone.setText(item.getPhone());
        holder.getBinding().layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.ClickItem(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
