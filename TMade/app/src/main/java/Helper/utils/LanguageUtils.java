package Helper.utils;

import android.content.res.Configuration;
import android.content.res.Resources;

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
    @SuppressWarnings("deprecation")
    public static void changeLanguage(Language language) {
        SharedPrefs.getInstance().put(SharedPrefs.LANGUAGE, language);
        sCurrentLanguage = language;
        Locale locale = new Locale(language.getCode());
        Resources resources = TMadeApp.self().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
