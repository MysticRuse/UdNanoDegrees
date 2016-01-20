package miro.sample.com.nanodegrees.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mitil on 1/15/16.
 */
public class NanoDegree {

    public static final String JSON_KEY_NANODEGREES = "degrees";
    private static final String JSON_KEY_COURSE_TITLE = "title";
    private static final String JSON_KEY_COURSE_IMAGE_URL = "image";
    private static final String JSON_KEY_COURSE_KEY = "key";

    private String courseTitle;
    private String courseImageUrl;
    private String courseKey;


    public NanoDegree(JSONObject fromJson) throws JSONException {

        String courseTitle = fromJson.getString(JSON_KEY_COURSE_TITLE);
        String url = fromJson.getString(JSON_KEY_COURSE_IMAGE_URL);
        String key = fromJson.getString(JSON_KEY_COURSE_KEY);

        this.courseTitle = courseTitle;
        this.courseImageUrl = url;
        this.courseKey = key;

    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseKey() {
        return courseKey;
    }

    public String getCourseImageUrl() {
        return courseImageUrl;
    }
}
