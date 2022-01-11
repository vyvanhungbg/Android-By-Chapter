package com.example.recyclerviewadvanced;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import java.util.List;

// Một apdapter cung cấp khả năng sort nhanh hơn . Tránh dùng notifydata set chang gây giảm hiệu năng
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private SortedList<Item> mSortedList;
    private IClickItemListener listener;

    public MyAdapter(IClickItemListener listener) {
        this.listener = listener;
        Log.e("MyAdapter contructor", "Call");
       mSortedList = new SortedList<Item>(Item.class, new SortedListAdapterCallback<Item>(this) {
           @Override
           public int compare(Item o1, Item o2) {
               return  o1.getName().compareTo(o2.getName());  // so sánh giúp các view hiển thị theo tên từ điển
           }

           @Override
           public boolean areContentsTheSame(Item oldItem, Item newItem) {
               return oldItem.getName().compareTo(newItem.getName()) ==0; /// kiêm tra xem 2 item cùng dữ liêu hay không
           }

           @Override
           public boolean areItemsTheSame(Item item1, Item item2) { /// kiểm tra xem có phải item đó không
               return  item1.getId() == (item1.getId());
           }


       });
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Just update the holder with the object in the sorted list from the given position
        Item model = mSortedList.get(position);
        if (model != null) {
            holder.setDataModel(model);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.ClickItem(model);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mSortedList.size();
    }
    public void resetList(List<Item> models) {
        //If you are performing multiple changes, use the batching methods to ensure proper

        mSortedList.beginBatchedUpdates();
        mSortedList.clear();
        mSortedList.addAll(models);
        mSortedList.endBatchedUpdates();
    }
    //The following methods each modify the data set and automatically handles calling the
    //appropriate 'notify' method on the adapter.
    public void addModel(Item model) {
        mSortedList.add(model);
    }
    public void addModels(List<Item> models) {
        mSortedList.addAll(models);
    }
    public void clear() {
        mSortedList.clear();
    }
    public void removeModel(Item model) {
        mSortedList.remove(model);
    }
    public void removeModelAt(int i) {
        mSortedList.removeItemAt(i);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView numberPhone ;
        LinearLayout linearLayout;
        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            numberPhone = itemView.findViewById(R.id.tv_phone);
            linearLayout = itemView.findViewById(R.id.layout_item);
        }
        void setDataModel(Item model) {
            //Update your UI with the data model passed here...
            name.setText(model.getName());
            numberPhone.setText(model.getPhone());
        }
    }

    /*@Override  // dùng để hiển thị nhiều layout item trên cùng 1 adapter -> tìm hiểm thêm
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }*/
}
