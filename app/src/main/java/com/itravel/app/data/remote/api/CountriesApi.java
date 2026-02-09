package com.itravel.app.data.remote.api;

import com.itravel.app.data.remote.dto.CountryDto;
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
