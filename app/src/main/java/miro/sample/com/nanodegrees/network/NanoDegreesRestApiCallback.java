package miro.sample.com.nanodegrees.network;

import java.util.List;

import miro.sample.com.nanodegrees.data.NanoDegree;

/**
 * Created by mitil on 1/18/16.
 */
public interface NanoDegreesRestApiCallback {

    /**
     * Callback function for notifying success in fetching the NanoDegree data.
     *
     * @param nanoDegrees the list of {@code NanoDegree}
     */
    public void onSuccess(List<NanoDegree> nanoDegrees);

    /**
     * Callback function for notifying error in fetching the NanoDegree data.
     *
     * @param statusCode
     * @param message
     */
    public void onFailure(int statusCode, String message);
}
