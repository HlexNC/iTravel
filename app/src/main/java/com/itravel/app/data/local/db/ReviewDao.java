package com.itravel.app.data.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDao {
    @Insert
    long insert(ReviewEntity review);

    @Query("SELECT * FROM reviews WHERE locationId = :locationId ORDER BY timestamp DESC")
    LiveData<List<ReviewEntity>> getReviewsForLocation(long locationId);

    @Query("SELECT * FROM reviews WHERE userId = :userId ORDER BY timestamp DESC")
    List<ReviewEntity> getReviewsByUser(long userId);

    @Query("SELECT AVG(rating) FROM reviews WHERE locationId = :locationId")
    float getAverageRating(long locationId);
}
