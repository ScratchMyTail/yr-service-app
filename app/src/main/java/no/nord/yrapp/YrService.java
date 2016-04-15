package no.nord.yrapp;

import java.util.List;

import no.nord.yrapp.model.YrSok;
import no.nord.yrapp.model.YrURL;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by christerhansen on 14.04.16.
 */
public interface YrService {
    @GET("/location?")
    Call<YrURL> getYrURL(@Query("lat") double lat, @Query("lng") double lng);

    @GET("/sok?")
    Call<List<YrSok>> getPlaces(@Query("sok") String sok);
}
