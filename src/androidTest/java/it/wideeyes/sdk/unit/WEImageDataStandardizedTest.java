package it.wideeyes.sdk.unit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import it.wideeyes.sdk.R;
import it.wideeyes.sdk.models.WEImageDataStandardized;

/**
 * Created by lompadark on 6/12/15.
 */
public class WEImageDataStandardizedTest extends AndroidTestCase {

    public void testWrongParametersThrowException() {
        try {
            WEImageDataStandardized imageDataStandardized = new WEImageDataStandardized(bitmapForTest(), null);
            fail("this should throw an exception");
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("pointsList has to be not empty", e.getMessage());
        }
    }

    private Bitmap bitmapForTest() {
        return BitmapFactory.decodeResource(getContext().getResources(), R.drawable.shoes);
    }
}
