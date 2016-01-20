package miro.sample.com.nanodegrees;

import android.app.Application;
import android.util.Log;

import miro.sample.com.nanodegrees.data.NanoDegreesDataManager;

/**
 * Created by mitil on 1/18/16.
 */
public class NanoDegreesApplication extends Application {

    private static final String TAG = NanoDegreesApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "NanoD: onCreate()");

        // Initialize DataManager class and start the initial fetch of the nano degrees
        //NanoDegreesDataManager dm = NanoDegreesDataManager.getInstance();
        //dm.fetchNanoDegreesList();
    }
}
