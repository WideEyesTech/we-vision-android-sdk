package it.wideeyes.sdk.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lompadark on 5/19/15.
 */
public class WEResult {

    private final List<WEObject> both = new LinkedList<>();
    private final List<WEObject> color = new LinkedList<>();
    private final List<WEObject> shape = new LinkedList<>();

    public WEResult(String r) throws JSONException {
        JSONObject result = new JSONObject(r);
        boolean success = result.getBoolean("success");
        if(!success) {
            throw new JSONException("not success call");
        }
        JSONArray resultBoth = result.getJSONArray("result_both");
        JSONArray resultColor = result.getJSONArray("result_color");
        JSONArray resultShape = result.getJSONArray("result_shape");
        for (int i = 0; i < resultBoth.length(); ++i) {
            both.add(extractProductData(resultBoth.getJSONObject(i)));
        }
        for (int i = 0; i < resultColor.length(); ++i) {
            color.add(extractProductData(resultColor.getJSONObject(i)));
        }
        for (int i = 0; i < resultShape.length(); ++i) {
            shape.add(extractProductData(resultShape.getJSONObject(i)));
        }
    }

    private ArrayList<String> extractUrlImgs(JSONArray data) throws JSONException {
        ArrayList<String> r = new ArrayList<>(data.length());
        for(int i = 0; i < data.length(); i++) {
            r.add(data.getString(i));
        }
        return r;
    }

    private WEObject extractProductData(JSONObject data) throws JSONException {
        String productId = data.getString("ProductId");
        String productUrl = data.getString("ProductUrl");
        String productName = data.getString("ProductName");
        JSONArray imgUrls = data.getJSONArray("ImgUrls");
        return new WEObject(productId, productUrl, productName, extractUrlImgs(imgUrls));
    }

    public List<WEObject> both() {
        return both;
    }

    public List<WEObject> color() {
        return color;
    }

    public List<WEObject> shape() {
        return shape;
    }
}
