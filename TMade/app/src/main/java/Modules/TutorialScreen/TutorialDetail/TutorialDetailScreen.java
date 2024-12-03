package Modules.TutorialScreen.TutorialDetail;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.tmadecrochet.tmade.R;

import java.util.List;

import Data.Constant;
import Helper.utils.LanguageUtils;
import Modules.SymbolScreen.SymbolDetail.Step.StepAdapter;
import Services.Symbol.SymbolModel;
import Services.Symbol.SymbolStep;
import Services.Tutorial.TutorialItem;
import Services.Tutorial.TutorialModel;

public class TutorialDetailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tutorial_detail_screen);

        TutorialModel tutorialModel = (TutorialModel) getIntent().getSerializableExtra("TutorialModel");

        Toolbar toolbar = (Toolbar) findViewById(R.id.tutorial_detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ico_back);

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

        RecyclerView rcvTutorialDetailView = findViewById(R.id.rcv_tutorial_detail);

        if (tutorialModel != null) {

            String tutorialName =  getStringByIdName(this, tutorialModel.getTutorialName());
            if (!tutorialName.isEmpty()) {
                this.setTitle(tutorialName);
            } else {
                this.setTitle(null);
            }

            List<TutorialItem> items = tutorialModel.getList();
            if (!items.isEmpty()) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
                rcvTutorialDetailView.setLayoutManager(linearLayoutManager);
                TutorialDetailAdapter tutorialDetailAdapter = new TutorialDetailAdapter(this, this.getLifecycle());
                tutorialDetailAdapter.setData(items);
                rcvTutorialDetailView.setAdapter(tutorialDetailAdapter);
            }
        }

        initAds(getApplicationContext());

    }

    public static String getStringByIdName(Context context, String idName) {
        String resuls = "";
        Resources res = context.getResources();
        int resId = res.getIdentifier(idName, "string", context.getPackageName());
        if (resId > 0) {
            String resString = LanguageUtils.getLocaleStringResource(resId, context);
            if (!resString.isEmpty()) {
                resuls = resString;
            }
        }
        return resuls;
    }

    public void initAds(Context context) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        AdView adView = findViewById(R.id.tutorial_detail_banner_ad_view);
        AdRequest adRequest= new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
}