package com.example.recyclerviewadvanced;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
// lớp phụ trợ binding data cho một vài lớp adapter recycler
public class BindingViewHolder<T> extends RecyclerView.ViewHolder {
    private final T binding;
    public BindingViewHolder(@NonNull View itemView) {
        super(itemView);
        this.binding = (T) DataBindingUtil.bind(itemView);
    }

    public T getBinding() {
        return binding;
    }
}
