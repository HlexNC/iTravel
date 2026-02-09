package com.itravel.app.data.repository;

import com.itravel.app.data.remote.api.UnsplashApi;
import com.itravel.app.data.remote.client.RetrofitClient;
import com.itravel.app.data.remote.dto.UnsplashPhotoDto;
import com.itravel.app.model.FeedItem;
import com.itravel.app.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepository {

    public interface FeedCallback {
        void onSuccess(List<FeedItem> items);
        void onError(String message);
    }

    private final UnsplashApi unsplashApi;

    public FeedRepository() {
        unsplashApi = RetrofitClient.getUnsplashClient().create(UnsplashApi.class);
    }

    public void fetchFeedItems(FeedCallback callback) {
        String clientId = Constants.UNSPLASH_CLIENT_ID;
        if (clientId == null || clientId.isEmpty()) {
            callback.onError("No Unsplash API key configured");
            return;
        }

        unsplashApi.getRandomPhotos("Client-ID " + clientId, 10, "travel", "portrait")
                .enqueue(new Callback<List<UnsplashPhotoDto>>() {
                    @Override
                    public void onResponse(Call<List<UnsplashPhotoDto>> call,
                            Response<List<UnsplashPhotoDto>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<FeedItem> items = new ArrayList<>();
                            for (UnsplashPhotoDto photo : response.body()) {
                                String title = photo.location != null && photo.location.name != null
                                        ? photo.location.name : "Unknown Location";
                                String description = photo.description != null
                                        ? photo.description
                                        : (photo.altDescription != null
                                                ? photo.altDescription
                                                : "Beautiful travel moment");
                                String author = photo.user != null ? photo.user.name : "Unknown Author";

                                FeedItem item = new FeedItem(title, description,
                                        photo.urls.regular, author, 0);

                                if (photo.location != null && photo.location.position != null
                                        && photo.location.position.latitude != null) {
                                    item.setLatitude(photo.location.position.latitude);
                                    item.setLongitude(photo.location.position.longitude);
                                }

                                items.add(item);
                            }
                            callback.onSuccess(items);
                        } else {
                            callback.onError("Failed to load photos");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UnsplashPhotoDto>> call, Throwable t) {
                        callback.onError("Failed to load Unsplash: " + t.getMessage());
                    }
                });
    }
}
