package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

//ViewPager là một trình quản lý bố cục cho phép người dùng lật sang trái và phải qua các trang dữ liệu. Nó thường được sử dụng cùng với Fragment, đây là một cách thuận tiện để cung cấp và quản lý vòng đời của mỗi trang
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> images = new ArrayList<>();
        images.add(R.color.white);
        images.add(R.color.purple_200);
        images.add(R.color.teal_200);
                ViewPager pager = (ViewPager) findViewById(R.id.photos_viewpager);
        PagerAdapter adapter = new PhotosAdapter(images, this);
        pager.setAdapter(adapter);
        TabLayout tabLayout =  findViewById(R.id.tab_layout);  // Nếu thẻ xml viewpager bọc thể tablayout thì sẽ không cần gọi 2 dòng này để liên kết . Nếu ở ngoài viewpager thì cần gọi
        tabLayout.setupWithViewPager(pager, true);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //Phương thức này sẽ được gọi khi trang hiện tại được cuộn, như một phần của // cuộn trơn được khởi tạo theo chương trình hoặc cuộn cảm ứng do người dùng khởi tạo
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("Pos", position+"");
              //  Được gọi khi trạng thái cuộn thay đổi. Hữu ích để khám phá khi nào người dùng bắt đầu // kéo, khi máy nhắn tin tự động chuyển đến trang hiện tại, // hoặc khi nó dừng hoàn toàn / không hoạt động
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}