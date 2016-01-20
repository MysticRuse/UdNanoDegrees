package miro.sample.com.nanodegrees.data;

import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import miro.sample.com.nanodegrees.network.NanoDegreesRestApi;
import miro.sample.com.nanodegrees.network.NanoDegreesRestApiCallback;

/**
 * Created by mitil on 1/18/16.
 *
 * Singleton class used to store NanoDegrees data in memory.
 * TODO: To save the data peristently across app launches, may save the data in database and
 * load it in memory on launch of the application
 */
public class NanoDegreesDataManager {

    private static final String TAG = NanoDegreesDataManager.class.getSimpleName();

    /**
     * Interface for notifying listeners when there is any change in the Nano Degrees data
     */
    public interface NanoDegreesDataChangeListener {
        public void onDataChanged();
    }

    private static NanoDegreesDataManager instance = null;

    private static List<NanoDegree> nanoDegreesData = null;

    private List<NanoDegreesDataChangeListener> dataChangeListeners = null;


    private NanoDegreesDataManager() {
        nanoDegreesData = new ArrayList<NanoDegree>();
        dataChangeListeners = new ArrayList<NanoDegreesDataChangeListener>();
    }

    public static NanoDegreesDataManager getInstance() {
        if (instance == null) {
            instance = new NanoDegreesDataManager();
        }
        return instance;
    }

    public void registerDataChangeListener(NanoDegreesDataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    public void unregisterDataChangeListener(NanoDegreesDataChangeListener listener) {
        dataChangeListeners.remove(listener);
    }

    public void fetchNanoDegreesList() {
        final NanoDegreesRestApi api = new NanoDegreesRestApi();
        try {
            api.getNanoDegrees(new NanoDegreesRestApiCallback() {

                @Override
                public void onSuccess(List<NanoDegree> nanoDegrees) {
                    Log.i(TAG, "getNanoDegrees() onSuccess(): " + nanoDegrees);
                    nanoDegreesData = nanoDegrees;
                    notifyListeners();
                    //printData();
                }

                @Override
                public void onFailure(int statusCode, String message) {
                    Log.i(TAG, "getNanoDegrees() onFailure(): " + statusCode + "::" + message);
                }
            });
        } catch (JSONException exception) {
            Log.e(TAG, "getNanoDegrees() exception: " + exception);
        }
    }

    public List<NanoDegree> getNanoDegreesData() {
        Log.i(TAG, "getNanoDegrees(): size: " + nanoDegreesData.size());
        return nanoDegreesData;
    }

    private void notifyListeners() {
        for (NanoDegreesDataChangeListener listeners:dataChangeListeners) {
            listeners.onDataChanged();
        }
    }

    private void printData() {
        int i=1;
        for (NanoDegree course: nanoDegreesData) {
            Log.i(TAG, "course: (" + i + ") " + course.getCourseTitle());
            i++;
        }
    }
}
