package com.itravel.app.data.remote.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitCountries;
    private static Retrofit retrofitUnsplash;
    private static Retrofit retrofitWeather;

    public static Retrofit getCountriesClient() {
        if (retrofitCountries == null) {
            retrofitCountries = new Retrofit.Builder()
                    .baseUrl("https://restcountries.com/v3.1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitCountries;
    }

    public static Retrofit getUnsplashClient() {
        if (retrofitUnsplash == null) {
            retrofitUnsplash = new Retrofit.Builder()
                    .baseUrl("https://api.unsplash.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitUnsplash;
    }

    public static Retrofit getWeatherClient() {
        if (retrofitWeather == null) {
            retrofitWeather = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitWeather;
    }
}
