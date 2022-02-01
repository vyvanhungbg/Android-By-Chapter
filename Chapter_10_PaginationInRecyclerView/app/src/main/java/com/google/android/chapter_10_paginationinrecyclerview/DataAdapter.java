package com.google.android.chapter_10_paginationinrecyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.chapter_10_paginationinrecyclerview.my_interface.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends PagedListAdapter<Food,DataAdapter.ViewHolder> {




    protected DataAdapter() {
        super(diffCallback);
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = getItem(position);
        Picasso.get().load(food.getFoodImage()).into(holder.foodImage);
        holder.foodName.setText(food.getFoodName());
    }






    //
    private static DiffUtil.ItemCallback<Food> diffCallback =
            new DiffUtil.ItemCallback<Food>() {
                @Override
                public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.getIdFood() == newItem.getIdFood();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public static class ProgressBarHolder extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;
        public ProgressBarHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);

        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName,foodPrice;
        ImageView foodImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
        }
    }
}
