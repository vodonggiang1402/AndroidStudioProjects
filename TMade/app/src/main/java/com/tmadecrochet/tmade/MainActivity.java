package com.tmadecrochet.tmade;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tmadecrochet.tmade.fragment.CounterFragment;
import com.tmadecrochet.tmade.fragment.SettingFragment;
import com.tmadecrochet.tmade.fragment.SymbolFragment;
import com.tmadecrochet.tmade.fragment.TutorialFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_symbol);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        Fragment selectedFragment = new SymbolFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
    }

    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        int id = item.getItemId();
        Fragment selectedFragment = null;

        if (id == R.id.bottom_symbol) {
            selectedFragment =  new SymbolFragment();
        } else if (id == R.id.bottom_counter) {
            selectedFragment =  new CounterFragment();
        } else if (id == R.id.bottom_tutorial) {
            selectedFragment =  new TutorialFragment();
        } else if (id == R.id.bottom_setting) {
            selectedFragment =  new SettingFragment();
        }

        getSupportFragmentManager ().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();

        return true;
    };
}