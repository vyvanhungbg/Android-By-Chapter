package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class ActivityFragmentPageAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment_page_adapter);
//        ViewPager viewPager = findViewById(R.id.view_pager_in_activity_page_adapter);  // dùng cho viewpage vs tablayout
//        PagerAdapterWithFragmentPagerAdapter adapter = new PagerAdapterWithFragmentPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        TabLayout tabLayout = findViewById(R.id.tab_layout_in_activity_page_adapter);
//        tabLayout.setupWithViewPager(viewPager);

        setContentView(R.layout.view_page_2);  // set up cho viewPager2
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        //viewPager2.setPageTransformer();
    }
}