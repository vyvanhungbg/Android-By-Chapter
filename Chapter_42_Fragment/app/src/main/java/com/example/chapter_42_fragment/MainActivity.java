package com.example.chapter_42_fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chapter_42_fragment.ui.DashboardFragment;
import com.example.chapter_42_fragment.ui.HomeFragment;
import com.example.chapter_42_fragment.ui.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chapter_42_fragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("fragmentHome")
                    .replace(R.id.container, HomeFragment.newInstance(), "fragmentHome")
                    .commit();
        }
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        replaceFragment(HomeFragment.newInstance(), "fragmentHome");break;
                    case R.id.navigation_dashboard:
                        replaceFragment(DashboardFragment.newInstance(), "fragmentDash");break;
                    case R.id.navigation_notifications:
                        replaceFragment(NotificationsFragment.newInstance(), "fragmentNoti");break;
                }
                return false;
            }
        });

    }
    public void replaceFragment(Fragment fragment, String tag) {
        //Get current fragment placed in container
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        //Prevent adding same fragment on top
        if (currentFragment.getClass() == fragment.getClass()) {
            return;
        }
        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        //Otherwise, just replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsInStack > 1) { // If we have more than one fragment, pop back stack
            getSupportFragmentManager().popBackStack();
        } else if (fragmentsInStack == 1) { // Finish activity, if only one fragment left, to prevent
            //leaving empty screen
            finish();
        } else {
            super.onBackPressed();
        }
    }

}