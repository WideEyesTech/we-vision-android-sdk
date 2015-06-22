package it.wideeyes.sdk.unit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import it.wideeyes.sdk.R;
import it.wideeyes.sdk.models.WEImageData;
import it.wideeyes.sdk.models.WEImageDataStandardized;
import it.wideeyes.sdk.services.WEComputerVision;

/**
 * Created by lompadark on 6/12/15.
 */
public class WEComputerVisionTest extends AndroidTestCase {

    public void testItShouldThrowAnException() {
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.shoes);
        WEImageData imageData = new WEImageData(bitmap);
        WEImageDataStandardized imageDataStandardized = null;
        try {
            imageDataStandardized = WEComputerVision.standardize(imageData, 0);
            fail("this should throw exception");
        } catch (Exception e) {
            assertEquals("WEImageData has no points", e.getMessage());
        }
        assertNull(imageDataStandardized);
    }

    public void testItShouldNotThrowAnException() {
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.shoes);
        WEImageData imageData = new WEImageData(bitmap);
        imageData.addPair(1,2);
        WEImageDataStandardized imageDataStandardized = null;
        try {
            imageDataStandardized = WEComputerVision.standardize(imageData, 0);
        } catch (Exception e) {
            fail("this should not throw an exception");
            e.printStackTrace();
        }
        assertNotNull(imageDataStandardized);
    }
}
