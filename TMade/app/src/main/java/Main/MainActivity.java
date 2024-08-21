package Main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tmadecrochet.tmade.R;
import Modules.CounterScreen.CounterFragment;
import Modules.SettingScreen.SettingFragment;
import Modules.SymbolScreen.SymbolFragment;
import Modules.TutorialScreen.TutorialFragment;

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