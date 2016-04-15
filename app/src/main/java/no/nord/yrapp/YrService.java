package no.nord.yrapp;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by christerhansen on 14.04.16.
 */
public interface YrService {
    @GET("/location?")
    Call<YrURL> getYrURL(@Query("lat") double lat, @Query("lng") double lng);
}
