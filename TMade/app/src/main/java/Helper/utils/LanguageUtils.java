package Helper.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import Base.TMadeApp;
import Data.Constant;
import Services.Language.Language;

public class LanguageUtils {

    private static Language sCurrentLanguage = null;

    public static Language getCurrentLanguage() {
        if (sCurrentLanguage == null) {
            sCurrentLanguage = initCurrentLanguage();
        }
        return sCurrentLanguage;
    }

    /**
     * check language exist in SharedPrefs, if not exist then default language is English
     */
    private static Language initCurrentLanguage() {
        Language currentLanguage =
                SharedPrefs.getInstance().get(SharedPrefs.LANGUAGE, Language.class);
        if (currentLanguage != null) {
            return currentLanguage;
        }
        currentLanguage = new Language(Constant.Value.DEFAULT_LANGUAGE_ID,
                TMadeApp.self().getString(R.string.language_vietnamese),
                TMadeApp.self().getString(R.string.language_vietnamese_code));
        SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, currentLanguage);
        return currentLanguage;
    }

    /**
     * return language list from string.xml
     */
    public static List<Language> getLanguageData() {
        List<Language> languageList = new ArrayList<>();
        List<String> languageNames =
                Arrays.asList(TMadeApp.self().getResources().getStringArray(R.array.language_names));
        List<String> languageCodes =
                Arrays.asList(TMadeApp.self().getResources().getStringArray(R.array.language_codes));
        if (languageNames.size() != languageCodes.size()) {
            // error, make sure these arrays are same size
            return languageList;
        }
        for (int i = 0, size = languageNames.size(); i < size; i++) {
            languageList.add(new Language(i, languageNames.get(i), languageCodes.get(i)));
        }
        return languageList;
    }

    /**
     * load current locale and change language
     */
    public static void loadLocale() {
        changeLanguage(initCurrentLanguage());
    }

    /**
     * change app language
     */
    public static void changeLanguage(Language language) {
        try {
            SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, language);
            sCurrentLanguage = language;
            Locale locale = new Locale(language.getCode());
            Resources resources = TMadeApp.self().getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        } catch (Exception ex){
            Log.e("Error", String.valueOf(ex));
        }
    }

    public static String getLocaleStringResource(int resourceId, Context context) {
        String result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // use latest api
            Configuration config = new Configuration(context.getResources().getConfiguration());
            config.setLocale(new Locale(sCurrentLanguage.getCode()));
            result = context.createConfigurationContext(config).getText(resourceId).toString();
        }
        else { // support older android versions
            Resources resources = context.getResources();
            Configuration conf = resources.getConfiguration();
            Locale savedLocale = conf.locale;
            conf.locale = new Locale(sCurrentLanguage.getCode());
            resources.updateConfiguration(conf, null);

            // retrieve resources from desired locale
            result = resources.getString(resourceId);

            // restore original locale
            conf.locale = savedLocale;
            resources.updateConfiguration(conf, null);
        }
        return result;
    }
}
