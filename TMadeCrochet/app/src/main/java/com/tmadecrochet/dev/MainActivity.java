package com.tmadecrochet.dev;

import static androidx.core.view.accessibility.AccessibilityWindowInfoCompat.Api33Impl.getRoot;

import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.tmadecrochet.dev.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflaterO);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager2 = findViewById(R.id.viewPager);
//        viewPagerAdapter = new ViewPagerAdapter (this) ;
//        viewPager2.setAdapter(viewPagerAdapter);
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        frameLayout = findViewById(R.id.frameLayout);
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager2.setVisibility(View.VISIBLE);
//                frameLayout.setVisibility(View.GONE);
//                viewPager2.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                viewPager2.setVisibility(View.GONE);
//                frameLayout.setVisibility(View.VISIBLE);
//            }
//        });
//
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//
//            @Override
//            public void onPageSelected(int position) {
//                switch (position){
//                    case 0:
//                    case 1:
//                    case 2:
//                    case 3:
//                        tabLayout.getTabAt(position);
//                }
//                super.onPageSelected(position);
//            }
//        });
//
//        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.bo:
//                        return true;
//                    case R.id.bottom_counter:
//                        return true;
//                }
//                return false;
//            }
//        });
        binding.bottomNavigationView.setOnItemSelectedListener (item -> {
            switch (item.getItemId()){
                case R.id.bottom_symbol:
                    replaceFragment(new FragmentSymbol());
                    break;
                case R.id.bottom_counter:
                    replaceFragment(new FragmentCounter());
                    break;
                case R.id.bottom_tutorial:
                    replaceFragment(new FragmentTutorial());
                    break;
                case R.id.bottom_setting:
                    replaceFragment(new FragmentSetting());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit()
    }
}