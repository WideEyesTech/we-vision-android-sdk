package it.wideeyes.sdk.services;

import it.wideeyes.sdk.models.WEResult;

/**
 * Created by lompadark on 5/19/15.
 */
public interface WECallable {

    void success(WEResult result);
    void error(WEError error);
}
