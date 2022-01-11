package com.example.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.viewpager.SliderAdapter.SliderAdapterCustom;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapterCustom> {

    private List<Integer> mSliderItems;
    Context context;

    public SliderAdapter(List<Integer> mSliderItems, Context context) {
        this.mSliderItems = mSliderItems;
        this.context = context;
    }

    @Override
    public SliderAdapterCustom onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image,parent,false);
        return new SliderAdapterCustom(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterCustom viewHolder, int position) {
        viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(mSliderItems.get(position)));
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    public class SliderAdapterCustom extends ViewHolder{
        LinearLayout linearLayout;
        public SliderAdapterCustom(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout_image);
        }
    }


}
