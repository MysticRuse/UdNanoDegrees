package miro.sample.com.nanodegrees.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import miro.sample.com.nanodegrees.data.NanoDegree;

/**
 * Created by mitil on 1/17/16.
 */
public class NanoDegreesRestApi {

    private static final String TAG = NanoDegreesRestApi.class.getSimpleName();

    private static final String REST_API_COURSE_PATH = "courses?projection=internal";

    public void getNanoDegrees(final NanoDegreesRestApiCallback callback) throws JSONException {

        UdacityRestClient.get(REST_API_COURSE_PATH, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.i(TAG, "getNanoDegrees() onSuccess(): " + statusCode + ", " + headers + ", " + response);
                try {
                    JSONArray degrees = response.getJSONArray(NanoDegree.JSON_KEY_NANODEGREES);
                    List<NanoDegree> nanoDegreesList = new ArrayList<NanoDegree>();
                    for (int i = 0; i < degrees.length(); i++) {
                        JSONObject degree = (JSONObject) degrees.get(i);
                        NanoDegree data = new NanoDegree(degree);
                        nanoDegreesList.add(data);
                    }

                    if (callback != null) {
                        callback.onSuccess(nanoDegreesList);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException in fetching NanoDegrees: " + e);
                }


            }

            @Override
            public void onSuccess(final int statusCode, final Header[] headers,
                                  final JSONArray response) {
                Log.i(TAG, "getNanoDegrees() onSuccess(): " + statusCode + ", " + headers + ", " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "getNanoDegrees() onFailure(): " + statusCode +
                        ", errors: " + errorResponse);
                if (callback != null) {
                    // TODO: To implement error callback
                    //callback.onFailure(statusCode, errorResponse.toString());
                }
            }
        });
    }


}
