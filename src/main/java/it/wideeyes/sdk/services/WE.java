package it.wideeyes.sdk.services;

import android.content.Context;
import android.util.Log;

/**
 * Created by lompadark on 5/21/15.
 */
public class WE {

    private static WE instance;

    private Context context;
    private String apiKey;

    private WE() {}

    public synchronized static void initialize(Context context, String apiKey) {
        if (instance == null) {
            instance = new WE();
            instance.context = context;
            instance.apiKey = apiKey;
        } else {
            Log.e("WE", "It's not possible to change API key");
        }
    }

    static String getApiKey() {
        return instance.apiKey;
    }
}
