package com.itravel.app.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.itravel.app.util.Constants;

public class SessionManager {

    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        SharedPreferences encrypted = null;
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            encrypted = EncryptedSharedPreferences.create(
                    Constants.SHARED_PREFS_NAME,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            // Fallback to regular SharedPreferences
        }

        if (encrypted != null) {
            prefs = encrypted;
        } else {
            prefs = context.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        }
    }

    public void saveSession(long userId, String name, String email) {
        prefs.edit()
                .putLong(Constants.KEY_USER_ID, userId)
                .putString(Constants.KEY_USER_NAME, name)
                .putString(Constants.KEY_USER_EMAIL, email)
                .apply();
    }

    public boolean isLoggedIn() {
        return prefs.getLong(Constants.KEY_USER_ID, -1) != -1;
    }

    public long getUserId() {
        return prefs.getLong(Constants.KEY_USER_ID, -1);
    }

    public String getUserName() {
        return prefs.getString(Constants.KEY_USER_NAME, "");
    }

    public String getUserEmail() {
        return prefs.getString(Constants.KEY_USER_EMAIL, "");
    }

    public void clearSession() {
        prefs.edit().clear().apply();
    }
}
