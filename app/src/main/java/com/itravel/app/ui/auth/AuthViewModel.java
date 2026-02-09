package com.itravel.app.ui.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itravel.app.data.local.db.AppDatabase;
import com.itravel.app.data.local.db.UserEntity;
import com.itravel.app.data.local.prefs.SessionManager;
import com.itravel.app.data.repository.UserRepository;

public class AuthViewModel extends AndroidViewModel {

    public static class AuthResult {
        public final boolean success;
        public final String errorMessage;

        private AuthResult(boolean success, String errorMessage) {
            this.success = success;
            this.errorMessage = errorMessage;
        }

        public static AuthResult success() {
            return new AuthResult(true, null);
        }

        public static AuthResult error(String message) {
            return new AuthResult(false, message);
        }
    }

    private final UserRepository userRepository;
    private final SessionManager sessionManager;
    private final MutableLiveData<AuthResult> authResult = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        userRepository = new UserRepository(db.userDao());
        sessionManager = new SessionManager(application);
    }

    public LiveData<AuthResult> getAuthResult() {
        return authResult;
    }

    public void login(String email, String password) {
        userRepository.login(email, password, new UserRepository.AuthCallback() {
            @Override
            public void onSuccess(UserEntity user) {
                sessionManager.saveSession(user.id, user.name, user.email);
                authResult.postValue(AuthResult.success());
            }

            @Override
            public void onError(String message) {
                authResult.postValue(AuthResult.error(message));
            }
        });
    }

    public void register(String name, String email, String password) {
        userRepository.register(name, email, password, new UserRepository.AuthCallback() {
            @Override
            public void onSuccess(UserEntity user) {
                sessionManager.saveSession(user.id, user.name, user.email);
                authResult.postValue(AuthResult.success());
            }

            @Override
            public void onError(String message) {
                authResult.postValue(AuthResult.error(message));
            }
        });
    }
}
