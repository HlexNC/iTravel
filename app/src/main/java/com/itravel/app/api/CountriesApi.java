package com.itravel.app.api;

import com.itravel.app.models.CountryDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountriesApi {
    @GET("all?fields=name,flags,region")
    Call<List<CountryDto>> getAllCountries();

    @GET("name/{name}?fields=name,flags,region")
    Call<List<CountryDto>> getCountryByName(@Path("name") String name);
}
