package Base;

import android.app.Application;
import com.google.gson.Gson;

public class TMadeApp extends Application {

    private static TMadeApp sInstance;
    private Gson mGSon;

    public static TMadeApp self() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mGSon = new Gson();
    }

    public Gson getGSon() {
        return mGSon;
    }
}
