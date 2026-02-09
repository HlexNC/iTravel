package com.itravel.app.util;

import com.itravel.app.BuildConfig;

public class Constants {
    public static final String SHARED_PREFS_NAME = "iTravel_prefs";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_EMAIL = "user_email";

    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1001;
    public static final int REQUEST_CODE_CAMERA = 1002;

    public static final float MAX_REVIEW_DISTANCE_METERS = 100f;
    public static final int MIN_REVIEW_LENGTH = 50;
    public static final int MAX_REVIEW_IMAGES = 5;

    // API Keys from BuildConfig
    public static final String UNSPLASH_CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY;
    public static final String WEATHER_API_KEY = BuildConfig.WEATHER_API_KEY;

    // Database
    public static final String DB_NAME = "itravel_db";
}
