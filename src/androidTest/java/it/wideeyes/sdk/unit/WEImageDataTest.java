package it.wideeyes.sdk.unit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import it.wideeyes.sdk.R;
import it.wideeyes.sdk.models.WEImageData;

/**
 * Created by lompadark on 6/12/15.
 */
public class WEImageDataTest extends AndroidTestCase {

    public void testBitmapIsNotNull() {
        WEImageData imageData = new WEImageData(bitmapForTest());
        assertNotNull(imageData.bitmap());
    }

    public void testAddPointSuccessfully() {
        WEImageData imageData = new WEImageData(bitmapForTest());
        assertEquals(imageData.pointsLength(), 0);

        imageData.addPair(3, 2);
        assertEquals(imageData.pointsLength(), 1);
        assertEquals(imageData.point(0).x, 3);
        assertEquals(imageData.point(0).y, 2);
    }

    private Bitmap bitmapForTest() {
        return BitmapFactory.decodeResource(getContext().getResources(), R.drawable.shoes);
    }
}
