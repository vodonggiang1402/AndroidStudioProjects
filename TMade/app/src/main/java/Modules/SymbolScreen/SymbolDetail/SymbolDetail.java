package Modules.SymbolScreen.SymbolDetail;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Modules.SymbolScreen.SymbolDetail.Step.StepAdapter;
import Services.SymbolModel;
import Services.SymbolStep;

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
            }

            List<SymbolStep> steps = symbolModel.getSteps();
            if (!steps.isEmpty()) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
                rcvStepView.setLayoutManager(linearLayoutManager);
                StepAdapter stepAdapter = new StepAdapter(this);
                stepAdapter.setData(steps);
                rcvStepView.setAdapter(stepAdapter);
            }
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
}