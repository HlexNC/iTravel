package com.itravel.app.data.local.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(LikeEntity like);

    @Query("DELETE FROM likes WHERE userId = :userId AND locationId = :locationId")
    void delete(long userId, long locationId);

    @Query("SELECT COUNT(*) FROM likes WHERE locationId = :locationId")
    int getLikeCount(long locationId);

    @Query("SELECT COUNT(*) > 0 FROM likes WHERE userId = :userId AND locationId = :locationId")
    boolean isLikedByUser(long userId, long locationId);
}
