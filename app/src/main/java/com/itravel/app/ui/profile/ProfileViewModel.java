package com.itravel.app.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itravel.app.data.local.db.AppDatabase;
import com.itravel.app.data.local.db.LocationDao;
import com.itravel.app.data.local.db.LocationEntity;
import com.itravel.app.data.local.db.ReviewDao;
import com.itravel.app.data.local.db.ReviewEntity;
import com.itravel.app.data.local.prefs.SessionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class ProfileViewModel extends AndroidViewModel {

    private final ReviewDao reviewDao;
    private final LocationDao locationDao;
    private final SessionManager sessionManager;

    private final MutableLiveData<String> userName = new MutableLiveData<>();
    private final MutableLiveData<String> userEmail = new MutableLiveData<>();
    private final MutableLiveData<Integer> reviewCount = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> visitedCount = new MutableLiveData<>(0);
    private final MutableLiveData<Float> avgRating = new MutableLiveData<>(0f);
    private final MutableLiveData<List<ReviewEntity>> userReviews = new MutableLiveData<>();
    private final MutableLiveData<Map<Long, String>> locationNames = new MutableLiveData<>(new HashMap<>());

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        reviewDao = db.reviewDao();
        locationDao = db.locationDao();
        sessionManager = new SessionManager(application);
    }

    public void loadProfile() {
        userName.setValue(sessionManager.getUserName());
        userEmail.setValue(sessionManager.getUserEmail());

        long userId = sessionManager.getUserId();
        if (userId == -1) return;

        Executors.newSingleThreadExecutor().execute(() -> {
            int reviews = reviewDao.getReviewCountByUser(userId);
            reviewCount.postValue(reviews);

            int visited = reviewDao.getVisitedLocationCountByUser(userId);
            visitedCount.postValue(visited);

            float avg = reviewDao.getAverageRatingByUser(userId);
            avgRating.postValue(avg);

            List<ReviewEntity> myReviews = reviewDao.getReviewsByUser(userId);
            userReviews.postValue(myReviews);

            // Load location names for reviews
            Map<Long, String> names = new HashMap<>();
            for (ReviewEntity review : myReviews) {
                if (!names.containsKey(review.locationId)) {
                    LocationEntity loc = locationDao.getLocationById(review.locationId);
                    names.put(review.locationId, loc != null ? loc.name : "Unknown Location");
                }
            }
            locationNames.postValue(names);
        });
    }

    public LiveData<String> getUserName() { return userName; }
    public LiveData<String> getUserEmail() { return userEmail; }
    public LiveData<Integer> getReviewCount() { return reviewCount; }
    public LiveData<Integer> getVisitedCount() { return visitedCount; }
    public LiveData<Float> getAvgRating() { return avgRating; }
    public LiveData<List<ReviewEntity>> getUserReviews() { return userReviews; }
    public LiveData<Map<Long, String>> getLocationNames() { return locationNames; }
}
