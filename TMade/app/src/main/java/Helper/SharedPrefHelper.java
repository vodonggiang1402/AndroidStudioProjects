package Helper;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import Services.Symbol.SymbolResponse;

public class SharedPrefHelper {
    // save data in sharedPrefences
    public static void setSharedOBJECT(Context context, String key,
                                       Object value) {

        SharedPreferences sharedPreferences =  context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    //get data from sharedPrefences
    public static <GenericClass> GenericClass getSharedOBJECT(Context context, String key, Class<GenericClass> classType) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        return gson.fromJson(json, classType);
    }
}

