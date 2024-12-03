package Modules.CounterScreen.CounterCategory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.gson.Gson;
import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

import Data.Constant;
import Helper.SharedPrefHelper;
import Modules.CounterScreen.Counter.CounterAdapter;
import Modules.CounterScreen.SelectICounterItemListener;
import Services.Counter.CounterModel;
import Services.Counter.CounterResponse;

public class CounterCategoryAdapter extends RecyclerView.Adapter<CounterCategoryAdapter.CounterCategoryViewHolder> implements SelectICounterItemListener {

    private final Context cContext;
    private ArrayList<CounterCategory> listCounterCategory;
    private CounterAdapter counterAdapter;
    private InterstitialAd cInterstitialAd;
    private final Activity cActivity;

    public CounterCategoryAdapter(Context cContext, Activity activity) {
        this.cContext = cContext;
        this.cActivity = activity;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<CounterCategory> list) {
        this.listCounterCategory =  list;
        initInterstitialAds(cContext);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CounterCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_category, parent, false);
        return new CounterCategoryAdapter.CounterCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterCategoryViewHolder holder, int position) {
        CounterCategory counterCategory = listCounterCategory.get(position);

        if (counterCategory == null) {
            return;
        }

        holder.nameCounterCategory.setText(counterCategory.getCounterCategoryName());
        holder.nameCounterCategory.setCompoundDrawablesWithIntrinsicBounds(counterCategory.getIconName(), 0, 0, 0);
        holder.buttonCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, counterCategory.getIconActionName(), 0);
        holder.buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterCategory.isGlobal()) {

                } else {
                    showDialogAddCounterTitle(counterCategory);
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.cContext, RecyclerView.VERTICAL,false);
        holder.rcvCounterCategory.setLayoutManager(linearLayoutManager);
        counterAdapter  = new CounterAdapter(this.cContext, this, cActivity);
        counterAdapter.setData(counterCategory.getCounters());
        holder.rcvCounterCategory.setAdapter(counterAdapter);
    }

    @Override
    public int getItemCount() {
        if (listCounterCategory != null) {
            return listCounterCategory.size();
        }
        return 0;
    }

    @Override
    public void onUpdateItemClicked() {
        updateDataLocal();
    }

    public static class CounterCategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameCounterCategory;
        private final RecyclerView rcvCounterCategory;
        private final Button buttonCategory;

        public CounterCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCounterCategory = itemView.findViewById(R.id.counter_category_title);
            buttonCategory = itemView.findViewById(R.id.counter_refresh_button);
            rcvCounterCategory = itemView.findViewById(R.id.rcv_counter_category);
        }
    }

    private void showDialogAddCounterTitle(CounterCategory counterCategory)
    {
        final Dialog dialog = new Dialog(cContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_counter_bottom_sheet_layout);

        EditText editText = (EditText)dialog.findViewById(R.id.counter_add_edit_text);

        Button okBtn = dialog.findViewById(R.id.counter_ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAds(editText.getText().toString());
                dialog.dismiss();
            }
        });

        Button cancelBtn = dialog.findViewById(R.id.counter_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT) );
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void updateDataLocal() {
        ArrayList<ArrayList<CounterModel>> list = new ArrayList<ArrayList<CounterModel>>();
        CounterCategory mainCounterCategory =  listCounterCategory.get(0);
        list.add(mainCounterCategory.getCounters());

        CounterCategory extraCounterCategory =  listCounterCategory.get(1);
        list.add(extraCounterCategory.getCounters());

        Gson gson = new Gson();
        CounterResponse response = new CounterResponse(list);
        String responseString = gson.toJson(response);
        SharedPrefHelper.saveSharedOBJECT(cContext,"counter_response", responseString);

    }

    private boolean isNumeric(String string) {
        if(Pattern.matches("\\d{1,3}((\\.\\d{1,2})?)", string))
            return true;
        else
            return false;
    }

    private void initInterstitialAds(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        Log.e("initInterstitialAds", Constant.Ads.getInterstitialAdsId());
        InterstitialAd.load(context, Constant.Ads.getInterstitialAdsId(), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        cInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("sInterstitialAd", loadAdError.toString());
                        // Handle the error
                        cInterstitialAd = null;
                    }
                });
    }

    public void showAds(String titleCounter) {
        if (cInterstitialAd != null) {
            cInterstitialAd.show(cActivity);
            cInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("sInterstitialAd", "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.d("sInterstitialAd","onAdDismissedFullScreenContent");

                    if (!titleCounter.isEmpty()) {
                        counterAdapter.addItemData(new CounterModel(false, titleCounter, 1, "F76A89"));
                    } else {
                        counterAdapter.addItemData(new CounterModel(false, "New counter", 1, "F76A89"));
                    }
                    updateDataLocal();

                    initInterstitialAds(cContext);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.d("sInterstitialAd","onAdFailedToShowFullScreenContent");
                    cInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    Log.d("TAG","onAdImpression");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    Log.d("sInterstitialAd","onAdShowedFullScreenContent");
                }
            });
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }
}

