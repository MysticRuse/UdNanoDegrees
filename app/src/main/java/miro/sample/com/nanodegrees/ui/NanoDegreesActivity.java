package miro.sample.com.nanodegrees.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import miro.sample.com.nanodegrees.R;
import miro.sample.com.nanodegrees.data.NanoDegree;
import miro.sample.com.nanodegrees.data.NanoDegreesDataManager;

public class NanoDegreesActivity extends AppCompatActivity
        implements NanoDegreesDataManager.NanoDegreesDataChangeListener {

    private static final String TAG = NanoDegreesActivity.class.getSimpleName();
    private NanoDegreesAdapter courseListAdapter;
    private RecyclerView courseListView;
    private ProgressBar progressBar;
    private List<NanoDegree> courseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanodegrees_recyclerview);

        // Initialize recycler view
        courseListView = (RecyclerView) findViewById(R.id.nanodegrees_list);
        courseListView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.nanodegrees_progressbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        NanoDegreesDataManager dm = NanoDegreesDataManager.getInstance();
        dm.registerDataChangeListener(this);

        courseData = NanoDegreesDataManager.getInstance().getNanoDegreesData();
        if (courseData.isEmpty()) {
            dm.fetchNanoDegreesList();
            progressBar.setVisibility(View.VISIBLE);
        } else {
            if (courseListAdapter == null) {
                courseListAdapter = new NanoDegreesAdapter(this, courseData);
                courseListView.setAdapter(courseListAdapter);
            }
        }
    }

    @Override
    protected void onPause() {
        NanoDegreesDataManager dm = NanoDegreesDataManager.getInstance();
        dm.unregisterDataChangeListener(this);

        if (progressBar.isShown()) {
            progressBar.setVisibility(View.INVISIBLE);
        }
        super.onPause();
    }

    @Override
    public void onDataChanged() {

        Log.i(TAG, "onDataChanged()");

        runOnUiThread(new Runnable() {

            /**
             * Starts executing the active part of the class' code. This method is
             * called when a thread is started that has been created with a class which
             * implements {@code Runnable}.
             */
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                courseData =
                        NanoDegreesDataManager.getInstance().getNanoDegreesData();
                if (courseListAdapter == null) {

                    courseListAdapter = new NanoDegreesAdapter(
                            NanoDegreesActivity.this, courseData);
                    courseListView.setAdapter(courseListAdapter);
                } else {
                    courseListAdapter.updateData(courseData);
                }
            }
        });
    }
}
