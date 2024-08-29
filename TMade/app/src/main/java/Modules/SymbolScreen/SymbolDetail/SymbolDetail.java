package Modules.SymbolScreen.SymbolDetail;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tmadecrochet.tmade.R;

import java.util.Objects;

import Services.SymbolModel;

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


        //receive
        SymbolModel symbolModel = (SymbolModel) getIntent().getSerializableExtra("SymbolModel");

        TextView symbolTitle = findViewById(R.id.symbol_detail_title);
        RelativeLayout relativeLayout = findViewById(R.id.symbol_detail_title_relative_layout);
        if (symbolModel != null) {
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

            relativeLayout.setBackgroundColor(Color.parseColor("#"+ symbolModel.getBackgroundColor()));
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