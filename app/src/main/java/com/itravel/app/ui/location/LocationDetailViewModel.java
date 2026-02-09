package com.itravel.app.ui.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itravel.app.data.local.db.AppDatabase;
import com.itravel.app.data.local.db.LikeDao;
import com.itravel.app.data.local.db.LocationDao;
import com.itravel.app.data.local.db.LocationEntity;
import com.itravel.app.data.local.db.ReviewDao;
import com.itravel.app.data.local.db.ReviewEntity;
import com.itravel.app.data.local.db.UserDao;
import com.itravel.app.data.local.db.UserEntity;
import com.itravel.app.data.local.prefs.SessionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class LocationDetailViewModel extends AndroidViewModel {

    private final LocationDao locationDao;
    private final ReviewDao reviewDao;
    private final LikeDao likeDao;
    private final UserDao userDao;
    private final SessionManager sessionManager;

    private final MutableLiveData<LocationEntity> location = new MutableLiveData<>();
    private final MutableLiveData<Float> avgRating = new MutableLiveData<>(0f);
    private final MutableLiveData<Integer> reviewCount = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> isLiked = new MutableLiveData<>(false);
    private final MutableLiveData<Map<Long, String>> userNames = new MutableLiveData<>(new HashMap<>());
    private LiveData<List<ReviewEntity>> reviews;

    private long locationId;

    public LocationDetailViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        locationDao = db.locationDao();
        reviewDao = db.reviewDao();
        likeDao = db.likeDao();
        userDao = db.userDao();
        sessionManager = new SessionManager(application);
    }

    public void loadLocation(long id) {
        this.locationId = id;
        reviews = reviewDao.getReviewsForLocation(id);

        Executors.newSingleThreadExecutor().execute(() -> {
            LocationEntity loc = locationDao.getLocationById(id);
            location.postValue(loc);

            float avg = reviewDao.getAverageRating(id);
            avgRating.postValue(avg);

            long userId = sessionManager.getUserId();
            if (userId != -1) {
                boolean liked = likeDao.isLikedByUser(userId, id);
                isLiked.postValue(liked);
            }
        });
    }

    public void loadUserNames(List<ReviewEntity> reviewList) {
        Executors.newSingleThreadExecutor().execute(() -> {
            Map<Long, String> names = new HashMap<>();
            for (ReviewEntity review : reviewList) {
                if (!names.containsKey(review.userId)) {
                    UserEntity user = userDao.getUserByIdSync(review.userId);
                    names.put(review.userId, user != null ? user.name : "Traveler");
                }
            }
            userNames.postValue(names);
            reviewCount.postValue(reviewList.size());
        });
    }

    public void toggleLike() {
        long userId = sessionManager.getUserId();
        if (userId == -1) return;

        Executors.newSingleThreadExecutor().execute(() -> {
            boolean currentlyLiked = likeDao.isLikedByUser(userId, locationId);
            if (currentlyLiked) {
                likeDao.delete(userId, locationId);
                isLiked.postValue(false);
            } else {
                com.itravel.app.data.local.db.LikeEntity like = new com.itravel.app.data.local.db.LikeEntity();
                like.userId = userId;
                like.locationId = locationId;
                likeDao.insert(like);
                isLiked.postValue(true);
            }
        });
    }

    public LiveData<LocationEntity> getLocation() { return location; }
    public LiveData<Float> getAvgRating() { return avgRating; }
    public LiveData<Integer> getReviewCount() { return reviewCount; }
    public LiveData<Boolean> getIsLiked() { return isLiked; }
    public LiveData<List<ReviewEntity>> getReviews() { return reviews; }
    public LiveData<Map<Long, String>> getUserNames() { return userNames; }
    public long getLocationId() { return locationId; }
}
