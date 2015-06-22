package it.wideeyes.sdk.services;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

import it.wideeyes.sdk.models.WEImageData;
import it.wideeyes.sdk.models.WEImageDataStandardized;

public class WEComputerVision {

    static String TAG = "ComputerVision";

    // standardize bmp to a specific width
    public static WEImageDataStandardized standardize(WEImageData imageData, float margin) throws Exception {

        if (imageData.pointsLength() == 0) {
            throw new Exception("WEImageData has no points");
        }

        Bitmap bmp = imageData.bitmap();
        final int w = bmp.getWidth();
        final int h = bmp.getHeight();
        int x0 = Integer.MAX_VALUE, x1 = Integer.MIN_VALUE;
        int y0 = Integer.MAX_VALUE, y1 = Integer.MIN_VALUE;
        // find max and min of X and Y
        for (int i = 0; i < imageData.pointsLength(); i++) {
            if (imageData.point(i).x < x0) x0 = Math.round(imageData.point(i).x);
            if (imageData.point(i).x > x1) x1 = Math.round(imageData.point(i).x);
            if (imageData.point(i).y < y0) y0 = Math.round(imageData.point(i).y);
            if (imageData.point(i).y > y1) y1 = Math.round(imageData.point(i).y);
        }

        // compute center point
        Point c = new Point(Math.round((x1 + x0) * 0.5f), Math.round((y1 + y0) * 0.5f));

        // add margin to the original size
        int w2 = Math.round((x1 - x0 + 1) * (1 + 2.f * margin));
        int h2 = Math.round((y1 - y0 + 1) * (1 + 2.f * margin));

        // recompute the bounding box
        x0 = Math.min(w - 1, Math.max(0, Math.round(c.x - w2 * 0.5f)));
        x1 = Math.min(w - 1, Math.max(0, Math.round(c.x + w2 * 0.5f)));
        y0 = Math.min(h - 1, Math.max(0, Math.round(c.y - h2 * 0.5f)));
        y1 = Math.min(h - 1, Math.max(0, Math.round(c.y + h2 * 0.5f)));

        w2 = x1 - x0 + 1;
        h2 = y1 - y0 + 1;
        // crop image
        Bitmap croppedImg = Bitmap.createBitmap(bmp, x0, y0, w2, h2);

        // crop contour
        for (int i = 0; i < imageData.pointsLength(); i++) {
            imageData.point(i).x -= x0;
            imageData.point(i).y -= y0;
        }

        // resize image
        Bitmap bitmap;
        float standardSize = 400;
        float scale = 1.f;
        if (w2 > h2) {
            scale = (float) standardSize / w2;
            bitmap = Bitmap.createScaledBitmap(croppedImg, (int) standardSize, Math.round(h2 * scale), true);
        } else {
            scale = (float) standardSize / h2;
            bitmap = Bitmap.createScaledBitmap(croppedImg, Math.round(w2 * scale), (int) standardSize, true);
        }


        ArrayList<Point> points = new ArrayList<>();
        // resize contour
        for (int i = 0; i < imageData.pointsLength(); i++) {
            int x = (int) (imageData.point(i).x * scale);
            int y = (int) (imageData.point(i).y * scale);
            points.add(new Point(x, y));
        }
        return new WEImageDataStandardized(bitmap, points);
    }
}