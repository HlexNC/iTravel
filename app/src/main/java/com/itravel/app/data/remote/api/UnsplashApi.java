package com.itravel.app.data.remote.api;

import com.itravel.app.data.remote.dto.UnsplashPhotoDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface UnsplashApi {
    @GET("photos/random")
    Call<List<UnsplashPhotoDto>> getRandomPhotos(
            @Header("Authorization") String clientId,
            @Query("count") int count,
            @Query("query") String query,
            @Query("orientation") String orientation);
}
