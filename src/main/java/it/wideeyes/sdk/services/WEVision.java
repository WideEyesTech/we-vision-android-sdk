package it.wideeyes.sdk.services;

import org.json.JSONException;

import it.wideeyes.sdk.models.WEGender;
import it.wideeyes.sdk.models.WEImageData;
import it.wideeyes.sdk.models.WEImageDataStandardized;

/**
 * Created by lompadark on 5/19/15.
 */
public class WEVision {

    private final static String SEARCH_BY_ID = "SearchById";
    private final static String SEARCH_BY_IMAGE = "SearchByImage";
    private final static String CROSS_SEARCH_BY_ID = "CrossSearchById";

    private WEVision(){}

    public static void searchById(WEGender gender, String category, String subCategory, String productId, final WECallable callable) {
        searchById(gender, category, subCategory, productId, 5, 100, callable);
    }

    public static void searchById(WEGender gender, String category, String subCategory, String productId, int minNumResults, int maxNumResults, WECallable callable) {
        try {
            WEQuery query = new WEQuery(SEARCH_BY_ID, gender, category, subCategory, productId, minNumResults, maxNumResults);
            new WEService(query, callable).execute();
        } catch (JSONException e) {
            callable.error(new WEError(e.getMessage()));
        }
    }

    public static void searchByImage(WEGender gender, String category, String subCategory, WEImageDataStandardized imageData, final WECallable callable) {
        try {
            WEImageQuery query = new WEImageQuery(SEARCH_BY_IMAGE, gender, category, subCategory, imageData);
            new WEService(query, callable).execute();
        } catch (JSONException e) {
            callable.error(new WEError(e.getMessage()));
        }
    }

    public static void searchByImage(WEGender gender, String category, String subCategory, WEImageData imageData, final WECallable callable){
        try {
            searchByImage(gender, category, subCategory, WEComputerVision.standardize(imageData, 0), callable);
        } catch (Exception e) {
            callable.error(new WEError(e.getMessage()));
        }
    }

    public static void searchByImage(WEGender gender, String category, String subCategory, WEImageData imageData, int margin, final WECallable callable) {
        try {
            searchByImage(gender, category, subCategory, WEComputerVision.standardize(imageData, margin), callable);
        } catch (Exception e) {
            callable.error(new WEError(e.getMessage()));
        }
    }

    public static void crossSearchById(WEGender gender, String category, String subCategory, String productId, final WECallable callable) {
        try {
            WEQuery query = new WEQuery(CROSS_SEARCH_BY_ID, gender, category, subCategory, productId);
            new WEService(query, callable).execute();
        } catch (JSONException e) {
            callable.error(new WEError(e.getMessage()));
        }
    }
}
