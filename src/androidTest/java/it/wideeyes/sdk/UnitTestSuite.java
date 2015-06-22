package it.wideeyes.sdk;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by lompadark on 6/12/15.
 */
public class UnitTestSuite extends TestSuite {

    public static Test suite() {
        return new TestSuiteBuilder(ApplicationTest.class).includeAllPackagesUnderHere().build();
    }
}
