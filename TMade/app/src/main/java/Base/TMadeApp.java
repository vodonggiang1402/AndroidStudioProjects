package Base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.gson.Gson;
import com.tmadecrochet.tmade.BuildConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Data.Constant;

public class TMadeApp extends Application implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private static TMadeApp sInstance;
    private Gson mGSon;

    private Activity currentActivity;
    private AppOpenAdManager appOpenAdManager;

    public static TMadeApp self() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mGSon = new Gson();

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});

                    if (BuildConfig.DEBUG) {
                        List<String> testDeviceIds = Collections.singletonList("83e18d4b12a81912a807ce957ab91419");
                        RequestConfiguration configuration =
                                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
                        MobileAds.setRequestConfiguration(configuration);
                    }

                })
                .start();

        appOpenAdManager = new AppOpenAdManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.registerActivityLifecycleCallbacks(this);
        }
    }

    public Gson getGSon() {
        return mGSon;
    }


    /** Inner class that loads and shows app open ads. */
    private static class AppOpenAdManager {
        private static final String LOG_TAG = "AppOpenAdManager";

        private AppOpenAd appOpenAd = null;
        private boolean isLoadingAd = false;
        private boolean isShowingAd = false;

        /** Keep track of the time an app open ad is loaded to ensure you don't show an expired ad. */
        private long loadTime = 0;

        /** Constructor. */
        public AppOpenAdManager() {}

        /** Request an ad. */
        public void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return;
            }

            isLoadingAd = true;
            AdRequest request = new AdRequest.Builder().build();
            AppOpenAd.load(
                    context, Constant.Ads.getOpenAppAdsId(), request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull AppOpenAd ad) {
                            // Called when an app open ad has loaded.
                            Log.d(LOG_TAG, "Ad was loaded.");
                            appOpenAd = ad;
                            isLoadingAd = false;
                            loadTime = (new Date()).getTime();
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Called when an app open ad has failed to load.
                            Log.d(LOG_TAG, loadAdError.getMessage());
                            isLoadingAd = false;
                        }
                    });
        }

        /** Utility method to check if ad was loaded more than n hours ago. */
        private boolean wasLoadTimeLessThanNHoursAgo(double numHours) {
            long dateDifference = (new Date()).getTime() - this.loadTime;
            long numMilliSecondsPerHour = 3600000;
            return (dateDifference < (numMilliSecondsPerHour * numHours));
        }

        /** Check if ad exists and can be shown. */
        public boolean isAdAvailable() {
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(Constant.Timer_Ads.OPEN_APP);
        }

        public void showAdIfAvailable(
                @NonNull final Activity activity,
                @NonNull OnShowAdCompleteListener onShowAdCompleteListener){
            // If the app open ad is already showing, do not show the ad again.
            if (isShowingAd) {
                Log.d(LOG_TAG, "The app open ad is already showing.");
                return;
            }

            // If the app open ad is not available yet, invoke the callback then load the ad.
            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.");
                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
                return;
            }

            appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                    // Called when fullscreen content is dismissed.
                    // Set the reference to null so isAdAvailable() returns false.
                    Log.d(LOG_TAG, "Ad dismissed fullscreen content.");
                    appOpenAd = null;
                    isShowingAd = false;

                    onShowAdCompleteListener.onShowAdDismiss();
                    loadAd(activity);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);

                    // Called when fullscreen content failed to show.
                    // Set the reference to null so isAdAvailable() returns false.
                    Log.d(LOG_TAG, adError.getMessage());
                    appOpenAd = null;
                    isShowingAd = false;

                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }
            });

            isShowingAd = true;
            appOpenAd.show(activity);
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    /** Show the ad if one isn't already showing. */
    public void showAdIfAvailable(@NonNull final Activity activity, OnShowAdCompleteListener onShowAdCompleteListener) {
        appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }

    /** Interface definition for a callback to be invoked when an app open ad is complete. */
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
        void onShowAdDismiss();
    }


}
