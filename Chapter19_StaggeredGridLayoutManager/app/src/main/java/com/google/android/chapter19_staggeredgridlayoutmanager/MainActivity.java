package com.google.android.chapter19_staggeredgridlayoutmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.android.chapter19_staggeredgridlayoutmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String url1 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2Fimage_vit_quay_item_basic_compressed.jpeg?alt=media&token=36cf312b-d8e7-4eef-81b2-35e3c72e93d2";
    String url2 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2Fimg_food_ticket1_compressed.jpeg?alt=media&token=25ef8e5f-2e11-4099-a7a3-e3eda02367b5";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2Fimg_pizaa_compressed.jpeg?alt=media&token=80d6276b-5b0c-4a9a-a587-f9711ead9401";
    String url4 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2F15_compressed.jpeg?alt=media&token=94fded95-bbd4-437d-8722-1bc1698ee099";
    String url5 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2F19_compressed.jpeg?alt=media&token=89ccfa7d-41b5-4e08-bdb8-d747aac31f3d";
    String url6 = "https://media.cooky.vn/images/blog-2016/nghe-thuat-trinh-bay-va-chup-anh-mon-an%208.jpg";
    String url7 = "https://aphoto.vn/wp-content/uploads/2019/03/avata-19.jpg";
    String url8 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB3Jp_FBFrS5sIBzKWuoB0lBltg7tA2Esjg&usqp=CAU";
    String url9 = "http://channel.mediacdn.vn/thumb_w/640/2019/11/14/photo-1-15737325683221621163088.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        List<Image> images = new ArrayList<>();
        images.add(new Image("Ảnh 1",url1));
        images.add(new Image("Ảnh 1",url1));

        images.add(new Image("Ảnh 5",url5));
        images.add(new Image("Ảnh 5",url5));
        images.add(new Image("Ảnh 5",url5));
        images.add(new Image("Ảnh 6",url6));
        images.add(new Image("Ảnh 7",url7));
        images.add(new Image("Ảnh 1",url1));

        images.add(new Image("Ảnh 4",url4));
        images.add(new Image("Ảnh 5",url5));
        images.add(new Image("Ảnh 2",url2));
        images.add(new Image("Ảnh 3",url3));
        images.add(new Image("Ảnh 7",url7));
        images.add(new Image("Ảnh 7",url7));
        images.add(new Image("Ảnh 7",url7));
        images.add(new Image("Ảnh 8",url8));
        images.add(new Image("Ảnh 9",url9));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(new ImageAdapter(images));
    }
}