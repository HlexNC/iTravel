package com.itravel.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.itravel.app.R;
import com.itravel.app.adapters.FeedAdapter;
import com.itravel.app.models.FeedItem;
import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                        @Nullable Bundle savedInstanceState) {
                return inflater.inflate(R.layout.fragment_feed, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFeed);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                // Initialize Adapter with empty list
                List<FeedItem> feedItems = new ArrayList<>();
                FeedAdapter adapter = new FeedAdapter(feedItems);
                recyclerView.setAdapter(adapter);

                // Fetch Data from Unsplash
                fetchFeedData(adapter, feedItems);
        }

        private void fetchFeedData(FeedAdapter adapter, List<FeedItem> feedItems) {
                com.itravel.app.api.UnsplashApi api = com.itravel.app.api.RetrofitClient.getUnsplashClient()
                                .create(com.itravel.app.api.UnsplashApi.class);
                String clientId = com.itravel.app.utils.Constants.UNSPLASH_CLIENT_ID;

                // If no key is set, use fallbacks
                if (clientId.equals("YOUR_UNSPLASH_ACCESS_KEY")) {
                        loadMockData(adapter, feedItems);
                        android.widget.Toast
                                        .makeText(getContext(), "Add Unsplash Key in Constants.java for real photos!",
                                                        android.widget.Toast.LENGTH_LONG)
                                        .show();
                        return;
                }

                api.getRandomPhotos("Client-ID " + clientId, 10, "travel", "portrait")
                                .enqueue(new retrofit2.Callback<List<com.itravel.app.models.UnsplashPhotoDto>>() {
                                        @Override
                                        public void onResponse(
                                                        retrofit2.Call<List<com.itravel.app.models.UnsplashPhotoDto>> call,
                                                        retrofit2.Response<List<com.itravel.app.models.UnsplashPhotoDto>> response) {
                                                if (response.isSuccessful() && response.body() != null) {
                                                        feedItems.clear();
                                                        for (com.itravel.app.models.UnsplashPhotoDto photo : response
                                                                        .body()) {
                                                                String title = photo.location != null
                                                                                && photo.location.name != null
                                                                                                ? photo.location.name
                                                                                                : "Unknown Location";
                                                                String description = photo.description != null
                                                                                ? photo.description
                                                                                : (photo.altDescription != null
                                                                                                ? photo.altDescription
                                                                                                : "Beautiful travel moment");
                                                                String author = photo.user != null ? photo.user.name
                                                                                : "Unknown Author";

                                                                FeedItem item = new FeedItem(title, description,
                                                                                photo.urls.regular, author, 0);
                                                                feedItems.add(item);

                                                                // Fetch Weather if coordinates exist
                                                                if (photo.location != null
                                                                                && photo.location.position != null
                                                                                && photo.location.position.latitude != null) {
                                                                        fetchWeatherForitem(item,
                                                                                        photo.location.position.latitude,
                                                                                        photo.location.position.longitude,
                                                                                        adapter);
                                                                }
                                                        }
                                                        adapter.notifyDataSetChanged();
                                                } else {
                                                        loadMockData(adapter, feedItems);
                                                }
                                        }

                                        @Override
                                        public void onFailure(
                                                        retrofit2.Call<List<com.itravel.app.models.UnsplashPhotoDto>> call,
                                                        Throwable t) {
                                                loadMockData(adapter, feedItems);
                                                android.widget.Toast.makeText(getContext(),
                                                                "Failed to load Unsplash: " + t.getMessage(),
                                                                android.widget.Toast.LENGTH_SHORT).show();
                                        }
                                });
        }

        private void fetchWeatherForitem(FeedItem item, double lat, double lon, FeedAdapter adapter) {
                String apiKey = com.itravel.app.utils.Constants.WEATHER_API_KEY;
                if (apiKey.equals("YOUR_WEATHER_API_KEY"))
                        return;

                com.itravel.app.api.WeatherApi api = com.itravel.app.api.RetrofitClient.getWeatherClient()
                                .create(com.itravel.app.api.WeatherApi.class);
                api.getCurrentWeather(lat, lon, apiKey, "metric")
                                .enqueue(new retrofit2.Callback<com.itravel.app.models.WeatherDto>() {
                                        @Override
                                        public void onResponse(retrofit2.Call<com.itravel.app.models.WeatherDto> call,
                                                        retrofit2.Response<com.itravel.app.models.WeatherDto> response) {
                                                if (response.isSuccessful() && response.body() != null
                                                                && response.body().main != null) {
                                                        String temp = Math.round(response.body().main.temp) + "°C";
                                                        String icon = null;
                                                        if (response.body().weather != null
                                                                        && !response.body().weather.isEmpty()) {
                                                                icon = response.body().weather.get(0).icon;
                                                        }
                                                        item.setWeather(temp, icon);
                                                        adapter.notifyDataSetChanged(); // Refresh to show weather
                                                }
                                        }

                                        @Override
                                        public void onFailure(retrofit2.Call<com.itravel.app.models.WeatherDto> call,
                                                        Throwable t) {
                                                // Ignore weather failures silently
                                        }
                                });
        }

        private void loadMockData(FeedAdapter adapter, List<FeedItem> feedItems) {
                feedItems.add(new FeedItem("Santorini, Greece (Mock)",
                                "The sunsets here are absolutely magical. Highly recommend checking out Oia.",
                                "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?q=80&w=2938&auto=format&fit=crop",
                                "SarahTravels", 120));
                feedItems.add(new FeedItem("Kyoto, Japan (Mock)",
                                "Walking through the Fushimi Inari shrine was a spiritual experience.",
                                "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?q=80&w=2940&auto=format&fit=crop",
                                "ZenMaster", 85));
                feedItems.add(new FeedItem("Banff, Canada (Mock)",
                                "Lake Louise looks even better in person. The turquoise water is unreal!",
                                "https://images.unsplash.com/photo-1533632359083-0185df1d4a6e?q=80&w=2600&auto=format&fit=crop",
                                "MountainHiker", 200));
                adapter.notifyDataSetChanged();
        }
}
