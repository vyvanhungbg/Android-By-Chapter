package com.google.android.filter_header_footer_enless_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {

    List<Item> list;
    List<Item> filterList;
    IClickItemListener listener;
    final static int FOOTER_VIEW = 1;
    final static int HEADER_VIEW = 2;

    public AdapterItem(List<Item> list, IClickItemListener listener) {
        this.list = list;
        this.listener = listener;
        filterList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == FOOTER_VIEW){
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foodter,parent,false);
            return new FooterViewHolder(view);
        }else if(viewType == HEADER_VIEW) {
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false);
            return new HeaderViewHolder(view);
        }else {
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            return new NormalViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER_VIEW;
        }
        if(position == list.size()){
            return FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            if(holder instanceof NormalViewHolder){
               holder.bindView(position);
            }else if(holder instanceof  FooterViewHolder){
                //// Nhớ công 1 ở size mới hiện footer
            }else if(holder instanceof  HeaderViewHolder){
                /// gán các thuộc tính quan binview
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return filterList.size()+1;  // Nhớ công 1 ở size mới hiện footer
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
            Item item = filterList.get(position);
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



    public class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    public class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    public class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the normal items
                }
            });
        }
    }


    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String text = charSequence.toString();
                if(text.isEmpty()){
                    filterList = list;
                }else {
                    filterList = list.stream().filter(item -> item.getName().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList; // gán list vừa lọc được trở về
                return filterResults;  // trả về một đối tượng filterresult
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (List<Item>) filterResults.values; // gán ở trên bằng filter list rồi thì ở đây k cần dòng này cũng oke ,notify là đc
                notifyDataSetChanged();
            }
        };
   }


}
