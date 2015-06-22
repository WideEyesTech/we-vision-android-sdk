package it.wideeyes.sdk.models;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by lompadark on 6/8/15.
 */
public class WEImageDataStandardized extends WEImageData {
    public WEImageDataStandardized(Bitmap bitmap, ArrayList<Point> pointsList) throws Exception {
        super(bitmap);
        if (pointsList == null || pointsList.size() == 0) {
            throw new Exception("pointsList has to be not empty");
        }
        points = pointsList;
    }
}
