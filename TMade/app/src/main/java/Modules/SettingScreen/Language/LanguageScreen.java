package Modules.SettingScreen.Language;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import Helper.utils.LanguageUtils;
import Services.Language.Language;

public class LanguageScreen extends AppCompatActivity implements ItemClickListener {

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

        languageAdapter = new LanguageAdapter(this);
        languageAdapter.setData(LanguageUtils.getLanguageData());

        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.setAdapter(languageAdapter);

    }

    private void onChangeLanguageSuccessfully(final Language language) {
        languageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
        finish();
    }


    @Override
    public void onClickItem(Language item) {
        if (!item.getCode().equals(LanguageUtils.getCurrentLanguage().getCode())) {
            onChangeLanguageSuccessfully(item);
        }
    }
}