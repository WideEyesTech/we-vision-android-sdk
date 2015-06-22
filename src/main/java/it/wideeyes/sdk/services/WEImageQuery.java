package it.wideeyes.sdk.services;

import android.graphics.Bitmap;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;

import it.wideeyes.sdk.models.WEGender;
import it.wideeyes.sdk.models.WEImageData;

/**
 * Created by lompadark on 6/8/15.
 */
class WEImageQuery extends WEQuery {

    public WEImageQuery(String ep, WEGender gender, String category, String subCategory, WEImageData iData) throws JSONException {
        super(ep, gender, category, subCategory);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        iData.bitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        put("Image", Base64.encodeToString(byteArray, Base64.DEFAULT));
        JSONArray contour = new JSONArray();
        for (int i = 0; i < iData.pointsLength(); ++i) {
            JSONArray point = new JSONArray();
            point.put(iData.point(i).x);
            point.put(iData.point(i).y);
            contour.put(point);
        }
        put("Contour", contour);
    }
}
