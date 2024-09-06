package Modules.SymbolScreen.SymbolDetail;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.tmadecrochet.tmade.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modules.SymbolScreen.SymbolDetail.Step.StepAdapter;
import Services.Symbol.SymbolModel;
import Services.Symbol.SymbolStep;

import android.content.Intent;


public class SymbolDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_symbol_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.symbol_detail_toolbar);
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

        RelativeLayout relativeLayout = findViewById(R.id.symbol_detail_title_relative_layout);
        TextView symbolTitle = findViewById(R.id.symbol_detail_title);
        TextView symbolDes = findViewById(R.id.symbol_detail_des);
        RecyclerView rcvStepView = findViewById(R.id.rcv_step_view);
        rcvStepView.setNestedScrollingEnabled(false);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        MaterialButton materialButton = findViewById(R.id.youtube_player_play_button);

        //receive
        SymbolModel symbolModel = (SymbolModel) getIntent().getSerializableExtra("SymbolModel");
        if (symbolModel != null) {

            relativeLayout.setBackgroundColor(Color.parseColor("#"+ symbolModel.getBackgroundColor()));

            String symbolName = getStringByIdName(this, symbolModel.getSymbolName());
            if (!symbolName.isEmpty()) {
                symbolTitle.setText(symbolName);
            }

            String iconName = symbolModel.getIconName();
            if (!iconName.isEmpty()) {
                int id = this.getResources().getIdentifier(iconName, "drawable",
                        this.getPackageName());
                symbolTitle.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);
            }

            String symbolDesText = getStringByIdName(this, symbolModel.getSymbolDes());
            if (!symbolDesText.isEmpty()) {
                symbolDes.setText(symbolDesText);
            } else {
                symbolDes.setVisibility(View.INVISIBLE);
                symbolDes.setHeight(0);
            }

            List<SymbolStep> steps = symbolModel.getSteps();
            if (!steps.isEmpty()) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
                rcvStepView.setLayoutManager(linearLayoutManager);
                StepAdapter stepAdapter = new StepAdapter(this);
                stepAdapter.setData(steps);
                rcvStepView.setAdapter(stepAdapter);
            }

            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = extractYTId(symbolModel.getVideoUrl());
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });

            materialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public static String getStringByIdName(Context context, String idName) {
        String resuls = "";
        Resources res = context.getResources();
        int resId = res.getIdentifier(idName, "string", context.getPackageName());
        if (resId > 0) {
            String resString = res.getString(resId);
            if (!resString.isEmpty()) {
                resuls = resString;
            }
        }
        return resuls;
    }

    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            vId = matcher.group(1);
        }
        return vId;
    }

}