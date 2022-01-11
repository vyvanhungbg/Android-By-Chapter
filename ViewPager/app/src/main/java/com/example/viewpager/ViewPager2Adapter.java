package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return BlankFragment.newInstance(R.color.purple_200);
            case 1: return BlankFragment.newInstance(R.color.teal_200);
            default: return BlankFragment.newInstance(R.color.teal_700);

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
