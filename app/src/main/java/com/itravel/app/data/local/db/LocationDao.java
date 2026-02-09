package com.itravel.app.data.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insertAll(List<LocationEntity> locations);

    @Query("SELECT * FROM locations")
    LiveData<List<LocationEntity>> getAllLocations();

    @Query("SELECT * FROM locations WHERE category = :category")
    LiveData<List<LocationEntity>> getLocationsByCategory(String category);

    @Query("SELECT * FROM locations WHERE id = :id LIMIT 1")
    LocationEntity getLocationById(long id);

    @Query("SELECT * FROM locations WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    LiveData<List<LocationEntity>> searchLocations(String query);

    @Query("SELECT COUNT(*) FROM locations")
    int getCount();
}
