package com.itravel.app.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationUtils {

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        // Min 6 chars
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    public static boolean isValidReviewText(String text) {
        return !TextUtils.isEmpty(text) && text.trim().length() >= Constants.MIN_REVIEW_LENGTH;
    }
}
