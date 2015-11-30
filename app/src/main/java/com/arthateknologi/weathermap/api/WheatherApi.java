package com.arthateknologi.weathermap.api;

import com.arthateknologi.weathermap.model.Mweather;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public interface WheatherApi {
    @GET("/data/2.5/weather?appid=c87102b5049fce13208c25fbb350340b")
    Call<Mweather> getDataKota(@Query("q") String q);

    @GET("/data/2.5/weather?appid=c87102b5049fce13208c25fbb350340b")
    Call<Mweather> getDataLatling(
            @Query("lat") String lat,
            @Query("lon") String lon);
}
