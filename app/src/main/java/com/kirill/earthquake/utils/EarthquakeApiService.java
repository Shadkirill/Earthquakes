package com.kirill.earthquake.utils;

import com.kirill.earthquake.mvp.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthquakeApiService {
    static final String base_url = "https://earthquake.usgs.gov/";

    @GET("fdsnws/event/1/query?format=geojson")
    Call<ApiResponse> getEarthQuakes(@Query("starttime") String startTime, @Query("endtime") String endtime);
}
