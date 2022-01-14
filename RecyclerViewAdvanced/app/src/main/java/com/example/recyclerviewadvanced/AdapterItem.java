package com.example.recyclerviewadvanced;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {

    List<Item> list;
    IClickItemListener listener;
    int EMPTY_VIEW = 12345;

    public AdapterItem(List<Item> list, IClickItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nothing,parent,false);
       if(viewType != EMPTY_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
       }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(list.size() == 0 || list == null){
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = list.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPhone.setText(item.getPhone());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPhone;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvPhone = view.findViewById(R.id.tv_phone);
            linearLayout = view.findViewById(R.id.layout_item);
        }
    }
}
