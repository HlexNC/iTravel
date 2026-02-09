package com.itravel.app.data.repository;

import androidx.lifecycle.LiveData;

import com.itravel.app.data.local.db.LocationDao;
import com.itravel.app.data.local.db.LocationEntity;

import java.util.List;

public class LocationRepository {

    private final LocationDao locationDao;

    public LocationRepository(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public LiveData<List<LocationEntity>> getAllLocations() {
        return locationDao.getAllLocations();
    }

    public LiveData<List<LocationEntity>> getLocationsByCategory(String category) {
        return locationDao.getLocationsByCategory(category);
    }

    public LiveData<List<LocationEntity>> searchLocations(String query) {
        return locationDao.searchLocations(query);
    }

    public LocationEntity getLocationById(long id) {
        return locationDao.getLocationById(id);
    }
}
