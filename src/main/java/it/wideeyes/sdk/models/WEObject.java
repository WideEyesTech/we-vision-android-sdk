package it.wideeyes.sdk.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lompadark on 5/21/15.
 */
public class WEObject {
    private String productId;
    private String productUrl;
    private String productName;
    private ArrayList<String> imgUrls;

    public WEObject(String pId, String pUrl, String pName, ArrayList<String > lImgUrls) {
        productId = pId;
        productUrl = pUrl;
        productName = pName;
        imgUrls = lImgUrls;
    }

    public String productId() {
        return productId;
    }

    public String productUrl() {
        return productUrl;
    }

    public String productName() {
        return productName;
    }

    public List<String> imgUrls() {
        return imgUrls;
    }
}
