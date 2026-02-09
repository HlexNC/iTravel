package com.itravel.app.ui.discover;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.itravel.app.data.local.db.AppDatabase;
import com.itravel.app.data.local.db.LocationEntity;
import com.itravel.app.data.repository.LocationRepository;

import java.util.List;

public class DiscoverViewModel extends AndroidViewModel {

    private final LocationRepository locationRepository;
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>();
    private final MediatorLiveData<List<LocationEntity>> filteredLocations = new MediatorLiveData<>();
    private LiveData<List<LocationEntity>> currentSource;

    public DiscoverViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        locationRepository = new LocationRepository(db.locationDao());

        // Default: show all locations
        currentSource = locationRepository.getAllLocations();
        filteredLocations.addSource(currentSource, filteredLocations::setValue);
    }

    public LiveData<List<LocationEntity>> getFilteredLocations() {
        return filteredLocations;
    }

    public void setCategory(String category) {
        selectedCategory.setValue(category);

        // Remove previous source
        if (currentSource != null) {
            filteredLocations.removeSource(currentSource);
        }

        // Switch source based on category
        if (category == null || category.isEmpty() || category.equals("All")) {
            currentSource = locationRepository.getAllLocations();
        } else {
            currentSource = locationRepository.getLocationsByCategory(category);
        }

        filteredLocations.addSource(currentSource, filteredLocations::setValue);
    }
}
