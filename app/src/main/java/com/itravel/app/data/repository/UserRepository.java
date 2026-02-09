package com.itravel.app.data.repository;

import com.itravel.app.data.local.db.UserDao;
import com.itravel.app.data.local.db.UserEntity;
import com.itravel.app.util.PasswordUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserRepository {

    public interface AuthCallback {
        void onSuccess(UserEntity user);
        void onError(String message);
    }

    private final UserDao userDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register(String name, String email, String password, AuthCallback callback) {
        executor.execute(() -> {
            try {
                if (userDao.countByEmail(email) > 0) {
                    callback.onError("An account with this email already exists");
                    return;
                }

                String salt = PasswordUtils.generateSalt();
                String hash = PasswordUtils.hashPassword(password, salt);

                UserEntity user = new UserEntity();
                user.name = name;
                user.email = email;
                user.passwordHash = hash;
                user.passwordSalt = salt;

                long id = userDao.insert(user);
                user.id = id;
                callback.onSuccess(user);
            } catch (Exception e) {
                callback.onError("Registration failed. Please try again.");
            }
        });
    }

    public void login(String email, String password, AuthCallback callback) {
        executor.execute(() -> {
            try {
                UserEntity user = userDao.getUserByEmail(email);
                if (user == null) {
                    callback.onError("Invalid email or password");
                    return;
                }

                if (!PasswordUtils.verifyPassword(password, user.passwordSalt, user.passwordHash)) {
                    callback.onError("Invalid email or password");
                    return;
                }

                callback.onSuccess(user);
            } catch (Exception e) {
                callback.onError("Login failed. Please try again.");
            }
        });
    }
}
