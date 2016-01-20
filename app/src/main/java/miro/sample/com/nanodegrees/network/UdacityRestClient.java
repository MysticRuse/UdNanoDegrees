package miro.sample.com.nanodegrees.network;

import com.loopj.android.http.*;

/**
 * Created by mitil on 1/17/16.
 *
 * Base class for REST client that implements get and post requests using {@link AsyncHttpClient}
 */
public class UdacityRestClient {

    private static final String BASE_URL = "http://www.udacity.com/public-api/";
    private static final String API_VERSION = "v0/";

    private static AsyncHttpClient client = new AsyncHttpClient();


    /**
     * REST client get request
     *
     * @param relativeUrl
     * @param params
     * @param responseHandler
     */
    public static void get(String relativeUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(relativeUrl), params, responseHandler);
    }

    /**
     * REST client post request
     *
     * @param relativeUrl
     * @param params
     * @param responseHandler
     */
    public static void post(String relativeUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(relativeUrl), params, responseHandler);
    }

    /**
     * Returns the absolute URL from relative path
     *
     * @param relativeUrl
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + API_VERSION + relativeUrl;
    }

}
