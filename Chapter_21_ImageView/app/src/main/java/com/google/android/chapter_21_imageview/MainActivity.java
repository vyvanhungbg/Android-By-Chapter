package com.google.android.chapter_21_imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.chapter_21_imageview.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity  {

    // Khai báo Quyền internet
    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    String url1 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2F13_compressed.jpeg?alt=media&token=6e07abec-7fc1-47d6-9f63-276e82c52361";
    String url2 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2F19_compressed.jpeg?alt=media&token=89ccfa7d-41b5-4e08-bdb8-d747aac31f3d";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/onefood-54cc3.appspot.com/o/food%2F20_compressed.jpeg?alt=media&token=e98e4fd9-b92e-4e6e-aa62-03ed9b991228";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        // Chú ý với các thuộc tính căn ảnh như ScaleType [CenterCrop, FitXY, Matrix ...]
        // Sử dụng tint:"#.." để thay đổi màu sắc của ảnh
        //: Các mã màu "#rgb", "#argb", "#rrggbb", or "#aarrggbb"

        binding.cart2.setImageResource(R.drawable.ic_baseline_shopping_cart_24); // set anh từ drawable

        //Thay đổi màu sắc
        binding.cart2.setColorFilter((Color.argb(120, 255, 156, 38)));

        // Load ảnh bằng Pícasso
        Picasso.get().load(url1).placeholder(R.drawable.ic_baseline_shopping_cart_24).error(R.drawable.ic_baseline_shopping_cart_24).into(binding.cart3);

        //Load ảnh bằng Glide
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).applyDefaultRequestOptions(options).load(url2).into(binding.cart4);

        //Load ảnh bằng tay :)
       new LoadImageFromInternet(binding.cart5).execute(url3);






    }





    public class LoadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView = null;

        public LoadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            return download_Image(url);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String urlImage) {
            try {
                URL url = new URL(urlImage);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}