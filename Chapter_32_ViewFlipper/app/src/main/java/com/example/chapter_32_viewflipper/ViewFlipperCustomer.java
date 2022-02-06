package com.example.chapter_32_viewflipper;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewFlipperCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFlipperCustomer extends Fragment {


    public ViewFlipperCustomer() {
        // Required empty public constructor
    }


    public static ViewFlipperCustomer newInstance() {
        ViewFlipperCustomer fragment = new ViewFlipperCustomer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    ViewFlipper viewFlipper;
    FragmentManager fragmentManager;
    int gallery_grid_Images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image2, R.drawable.image4, R.drawable.image1,
            R.drawable.image2, R.drawable.image3, R.drawable.image4
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_flipper, container, false);
        viewFlipper = rootView.findViewById(R.id.view_flip);
        for (int i = 0; i < gallery_grid_Images.length; i++) {
            // This will create dynamic image views and add them to the ViewFlipper.
            setFlipperImage(gallery_grid_Images[i]);
        }
        return rootView;
    }


    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res + "");
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
        viewFlipper.setFlipInterval(3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewFlipper.setScrollIndicators(View.SCROLL_INDICATOR_BOTTOM);
        }
        viewFlipper.setAutoStart(true);
    }
}