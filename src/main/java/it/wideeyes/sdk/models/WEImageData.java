package it.wideeyes.sdk.models;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lompadark on 6/8/15.
 */
public class WEImageData {
    private Bitmap bitmap;
    protected List<Point> points = new ArrayList<>();

    public WEImageData(Bitmap img) {
        bitmap = img;
    }

    public void addPair(int x, int y) {
        points.add(new Point(x, y));
    }

    public int pointsLength() {
        return points.size();
    }

    public Point point(int i) {
        return points.get(i);
    }

    public Bitmap bitmap() {
        return bitmap;
    }

}
