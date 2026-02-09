package com.itravel.app.data.repository;

import com.itravel.app.data.remote.api.WeatherApi;
import com.itravel.app.data.remote.client.RetrofitClient;
import com.itravel.app.data.remote.dto.WeatherDto;
import com.itravel.app.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    public interface WeatherCallback {
        void onSuccess(String temp, String icon);
        void onError(String message);
    }

    private final WeatherApi weatherApi;

    public WeatherRepository() {
        weatherApi = RetrofitClient.getWeatherClient().create(WeatherApi.class);
    }

    public void fetchWeather(double lat, double lon, WeatherCallback callback) {
        String apiKey = Constants.WEATHER_API_KEY;
        if (apiKey == null || apiKey.isEmpty()) {
            callback.onError("No Weather API key configured");
            return;
        }

        weatherApi.getCurrentWeather(lat, lon, apiKey, "metric")
                .enqueue(new Callback<WeatherDto>() {
                    @Override
                    public void onResponse(Call<WeatherDto> call, Response<WeatherDto> response) {
                        if (response.isSuccessful() && response.body() != null
                                && response.body().main != null) {
                            String temp = Math.round(response.body().main.temp) + "\u00B0C";
                            String icon = null;
                            if (response.body().weather != null
                                    && !response.body().weather.isEmpty()) {
                                icon = response.body().weather.get(0).icon;
                            }
                            callback.onSuccess(temp, icon);
                        } else {
                            callback.onError("Weather data unavailable");
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherDto> call, Throwable t) {
                        callback.onError("Weather fetch failed: " + t.getMessage());
                    }
                });
    }
}
