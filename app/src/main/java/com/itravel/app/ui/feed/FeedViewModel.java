package com.itravel.app.ui.feed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itravel.app.data.repository.FeedRepository;
import com.itravel.app.data.repository.WeatherRepository;
import com.itravel.app.model.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class FeedViewModel extends AndroidViewModel {

    private final FeedRepository feedRepository;
    private final WeatherRepository weatherRepository;
    private final MutableLiveData<List<FeedItem>> feedItems = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public FeedViewModel(@NonNull Application application) {
        super(application);
        feedRepository = new FeedRepository();
        weatherRepository = new WeatherRepository();
    }

    public LiveData<List<FeedItem>> getFeedItems() {
        return feedItems;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadFeed() {
        isLoading.setValue(true);

        feedRepository.fetchFeedItems(new FeedRepository.FeedCallback() {
            @Override
            public void onSuccess(List<FeedItem> items) {
                feedItems.postValue(items);
                isLoading.postValue(false);

                // Fetch weather for items with coordinates
                for (FeedItem item : items) {
                    if (item.getLatitude() != 0 || item.getLongitude() != 0) {
                        fetchWeatherForItem(item);
                    }
                }
            }

            @Override
            public void onError(String message) {
                loadMockData();
                isLoading.postValue(false);
            }
        });
    }

    private void fetchWeatherForItem(FeedItem item) {
        weatherRepository.fetchWeather(item.getLatitude(), item.getLongitude(),
                new WeatherRepository.WeatherCallback() {
                    @Override
                    public void onSuccess(String temp, String icon) {
                        item.setWeather(temp, icon);
                        // Trigger UI update
                        feedItems.postValue(feedItems.getValue());
                    }

                    @Override
                    public void onError(String message) {
                        // Ignore weather failures silently
                    }
                });
    }

    private void loadMockData() {
        List<FeedItem> mockItems = new ArrayList<>();
        mockItems.add(new FeedItem("Santorini, Greece (Mock)",
                "The sunsets here are absolutely magical. Highly recommend checking out Oia.",
                "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?q=80&w=2938&auto=format&fit=crop",
                "SarahTravels", 120));
        mockItems.add(new FeedItem("Kyoto, Japan (Mock)",
                "Walking through the Fushimi Inari shrine was a spiritual experience.",
                "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?q=80&w=2940&auto=format&fit=crop",
                "ZenMaster", 85));
        mockItems.add(new FeedItem("Banff, Canada (Mock)",
                "Lake Louise looks even better in person. The turquoise water is unreal!",
                "https://images.unsplash.com/photo-1533632359083-0185df1d4a6e?q=80&w=2600&auto=format&fit=crop",
                "MountainHiker", 200));
        feedItems.postValue(mockItems);
    }
}
