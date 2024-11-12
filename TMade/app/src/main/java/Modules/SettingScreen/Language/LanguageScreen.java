package Modules.SettingScreen.Language;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.Locale;

import Data.ItemClickListener;
import Helper.LocaleHelper;
import Helper.SharedPrefHelper;
import Helper.utils.LanguageUtils;
import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolCategory.SymbolCategoryAdapter;
import Plash.SplashActivity;
import Services.Language.Language;
import Services.Symbol.SymbolModel;
import Services.Symbol.SymbolResponse;

public class LanguageScreen extends AppCompatActivity {

    private LanguageAdapter languageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_language_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.language_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ico_back);
        this.setTitle(null);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        RecyclerView rcvCategory = (RecyclerView) findViewById(R.id.rcv_language);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(layoutManager);

        languageAdapter = new LanguageAdapter();
        languageAdapter.setData(LanguageUtils.getLanguageData());
        languageAdapter.setListener(new ItemClickListener<Language>() {
            @Override
            public void onClickItem(Language language) {
                if (!language.getCode().equals(LanguageUtils.getCurrentLanguage().getCode())) {
                    onChangeLanguageSuccessfully(language);
                }
            }
        });

        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.setAdapter(languageAdapter);

    }

    private void onChangeLanguageSuccessfully(final Language language) {
        languageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
        finish();
    }
}