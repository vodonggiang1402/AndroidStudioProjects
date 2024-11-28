package Modules.SymbolScreen.Symbol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.tmadecrochet.tmade.R;

import java.util.List;

import Helper.utils.LanguageUtils;
import Modules.CounterScreen.SelectICounterItemListener;
import Modules.SymbolScreen.SelectSymbolItemListener;
import Modules.SymbolScreen.SymbolDetail.SymbolDetail;
import Services.Counter.CounterModel;
import Services.Symbol.SymbolModel;

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.SymbolViewHolder> {
    private final Context sContext;
    private List<SymbolModel> symbols;
    private final SelectSymbolItemListener listener;
    private InterstitialAd sInterstitialAd;
    private final Activity sActivity;

    public SymbolAdapter(Context sContext, SelectSymbolItemListener listener, Activity activity) {
        this.sContext = sContext;
        this.listener = listener;
        this.sActivity = activity;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<SymbolModel> list) {
        this.symbols = list;
        initAds(sContext);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateItemSymbolData(SymbolModel symbolModel) {
        symbolModel.setAds(false);
        this.listener.onUpdateItemClicked();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SymbolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symbol_item, parent, false);
        return new SymbolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymbolViewHolder holder, int position) {
        SymbolModel symbolModel = symbols.get(position);
        if (symbolModel == null) {
            return;
        }

        String iconName = symbolModel.getIconName();
        if (!iconName.isEmpty()) {
            Context context = holder.imageView.getContext();
            int id = context.getResources().getIdentifier(iconName, "drawable",
                    context.getPackageName());
            holder.imageView.setImageResource(id);
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }

        String symbolName =  getStringByIdName(sContext, symbolModel.getSymbolName());
        if (!symbolName.isEmpty()) {
            holder.textView.setText(symbolName);
        }
        holder.linearLayout.setBackgroundColor(Color.parseColor("#"+ symbolModel.getBackgroundColor()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (symbolModel.isAds()) {
                    if (sInterstitialAd != null) {
                        showAds(symbolModel);
                    } else {
                        initAds(sContext);
                    }
                } else {
                    Intent intent = new Intent(sContext, SymbolDetail.class);
                    intent.putExtra("SymbolModel", symbolModel);
                    sContext.startActivity(intent);
                }
            }
        });
        if (!symbolModel.isAds()) {
            holder.lockLinearLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void showAds(SymbolModel currentSymbolModel) {
        if (sInterstitialAd != null) {
            sInterstitialAd.show(sActivity);
            sInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("TAG", "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.d("TAG","onAdDismissedFullScreenContent");
                    initAds(sContext);
                    updateItemSymbolData(currentSymbolModel);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.d("TAG","onAdFailedToShowFullScreenContent");
                    sInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    Log.d("TAG","onAdImpression");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    Log.d("TAG","onAdShowedFullScreenContent");
                }
            });
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    @Override
    public int getItemCount() {
        if (symbols != null) {
            return symbols.size();
        }
        return 0;
    }

    public static class SymbolViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final LinearLayout linearLayout;
        private final ImageView imageView;
        private final TextView textView;
        private final LinearLayout lockLinearLayout;
        public SymbolViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.symbol_item_linear_layout);
            imageView = itemView.findViewById(R.id.img_symbol);
            textView = itemView.findViewById(R.id.symbol_title);
            cardView = itemView.findViewById(R.id.card_view);
            lockLinearLayout = itemView.findViewById(R.id.symbol_lock_linear_layout);
        }
    }

    public static String getStringByIdName(Context context, String idName) {
        String resuls = "";
        Resources res = context.getResources();
        int resId = res.getIdentifier(idName, "string", context.getPackageName());
        if (resId > 0) {
            String resString =  LanguageUtils.getLocaleStringResource(resId, context);
            if (!resString.isEmpty()) {
                resuls = resString;
            }
        }
        return resuls;
    }

    private void initAds(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context,"ca-app-pub-3940256099942544/4411468910", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        sInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        sInterstitialAd = null;
                    }
                });
    }

}
