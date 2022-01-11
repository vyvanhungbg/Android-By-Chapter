package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterWithFragmentPagerAdapter extends FragmentPagerAdapter {
    public PagerAdapterWithFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    } // 14-06-2021 FragmentPagerAdapter và FragmentStatePagerAdapter bị loại bỏ thay bàng viewPager2
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return BlankFragment.newInstance(R.color.black);
            case 1: return BlankFragment.newInstance(R.color.teal_200);
            default: return BlankFragment.newInstance(R.color.teal_700);

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return  "Page 1";
            case 1: return "Page 2";
            default: return "Page 3";

        }
    }
}
