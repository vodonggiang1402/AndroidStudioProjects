package Data;

import com.tmadecrochet.tmade.BuildConfig;

public class Constant {

    public static class Value {
        public static final int DEFAULT_LANGUAGE_ID = 0;
    }

    public static class RequestCode {
        public static final int CHANGE_LANGUAGE = 10000;
    }

    public static class PhoneNumber {
        public static final String PHONE = "+84357798368";
    }

    public static class Ads {
        public static String openAppAdsId;
        public static String interstitialAdsId;
        public static String bannerAdsId;

        public static String getOpenAppAdsId() {
            if (BuildConfig.DEBUG) {
                openAppAdsId =  "ca-app-pub-3940256099942544/5575463023";
            } else {
                openAppAdsId =  "ca-app-pub-9183925814024348/4095837925";
            }
            return openAppAdsId;
        }

        public static String getInterstitialAdsId() {
            if (BuildConfig.DEBUG) {
                interstitialAdsId =  "ca-app-pub-3940256099942544/4411468910";
            } else {
                interstitialAdsId =  "ca-app-pub-9183925814024348/6917442381";
            }
            return interstitialAdsId;
        }

        public static String getBannerAdsId() {
            if (BuildConfig.DEBUG) {
                bannerAdsId =  "ca-app-pub-3940256099942544/2435281174";
            } else {
                bannerAdsId =  "ca-app-pub-9183925814024348/9503141499";
            }
            return bannerAdsId;
        }
    }
}