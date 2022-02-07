package com.example.chapter_39_data_binding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter_39_data_binding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickItem{

    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Food food = new Food(R.drawable.image1, "Thịt boà", "120.000đ");

        //1) Binding item in activityMain lien ket mot chieu du lieu
        // Chú ý : cần khai báo anotation @BindingAdapter("ten arr khai trong thẻ image") VÀ PHẢI LÀ STATIC nếu kotlin dùng @JVMStatic
        //       : Sử dụng thư viện or bất cứ gì để load trong hàm dưới anotation trên trong class item đó ở đây là class Food
        //       : Xác định trong thẻ ImageView chỉ cần truyền url là đủ mặc dù trong hàm trên là hàm 2 đối số (View , url) ( phải có view mới set được ảnh trong class Food)

        binding.setFood(food);

        // Khi liên két như trên sẽ không cần id cho từng thẻ -> lắng nghe thay đổi -> cần liên kết 2 chiều dữ liệu
        // THAY ĐỔI @{}  bằng @={} .
        // Không kết hợp được với biểu thức toán tử 3 ngôi bị lỗi biên dịch bindingImpl
        //TextView textViewFoodName = findViewById(R.id.food_name);
        Log.e(TAG+" Food Name ",binding.getFood().getName().toString());
        Log.e(TAG+ " Food price ",binding.foodPrice.getText().toString());
       // Log.e(TAG+ " Food price find View",textViewFoodName.getText().toString());
        //binding.imageFood.setOnClickListener(new);


        // BIỂU THỨC trong thẻ food price activity main
        // tính toán trong thẻ vd nếu số tiền food == 0đ thì sẽ set là miễn phí còn không set giá thật
        binding.imageFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food.setName("PHở");
                Log.e(TAG,"Click");
                //binding.setFood(food);
            }
        });

       // #Binding set Onclick
        binding.setClick(this);


        //Binding with Recyclerview
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food(R.drawable.image1, "Thịt boà", "120.000đ"));
        foodList.add(new Food(R.drawable.image4, "Thịt 4", "120.000đ"));
        foodList.add(new Food(R.drawable.image3, "Thịt 2", "120.000đ"));
        foodList.add(new Food(R.drawable.image2, "Thịt 3", "120.000đ"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        AdapterFood adapterFood = new AdapterFood(this, foodList,this);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(adapterFood);
    }

    @Override
    public void onClick(Food food) {
        Toast.makeText(this,food.getName(),Toast.LENGTH_LONG).show();
    }
}