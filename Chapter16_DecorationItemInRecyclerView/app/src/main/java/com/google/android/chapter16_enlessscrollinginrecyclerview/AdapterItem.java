package com.google.android.chapter16_enlessscrollinginrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {

    List<Item> list;

    IClickItemListener listener;

    public AdapterItem(List<Item> list, IClickItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //Viet lại hàm này

        holder.bindView(position);

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
        public void bindView(int position) {
            Item item = list.get(position);
            tvName.setText(item.getName());
            tvPhone.setText(item.getPhone());
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.ClickItem(item);
                }
            });
        }

    }

}
