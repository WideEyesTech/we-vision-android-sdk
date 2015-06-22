package it.wideeyes.sdk.services;

import org.json.JSONException;
import org.json.JSONObject;

import it.wideeyes.sdk.models.WEGender;

/**
 * Created by lompadark on 5/21/15.
 */
class WEQuery extends JSONObject{

    public final String endPoint;

    public WEQuery(String ep, WEGender gender, String category, String subCategory, String productId, int minProducts, int maxProducts) throws JSONException {
        endPoint = ep;
        put("Gender", genderConverter(gender));
        put("Category", category);
        put("Subcategory", subCategory);
        put("ProductId", productId);
        put("MaxNumResults", maxProducts);
        put("MinNumResults", minProducts);
    }

    public WEQuery(String ep, WEGender gender, String category, String subCategory, String productId) throws JSONException {
        endPoint = ep;
        put("Gender", genderConverter(gender));
        put("Category", category);
        put("Subcategory", subCategory);
        put("ProductId", productId);
    }

    public WEQuery(String ep, WEGender gender, String category, String subCategory) throws JSONException {
        endPoint = ep;
        put("Gender", genderConverter(gender));
        put("Category", category);
        put("Subcategory", subCategory);
    }

    private String genderConverter(WEGender gender) {
        switch (gender) {
            case FEMALE:
                return "female";
            case MALE:
                return "male";
            case KID_BOY:
                return "kid_boy";
            case KID_GIRL:
                return "kid_girl";
            default:
                return "male";
        }
    }
}
