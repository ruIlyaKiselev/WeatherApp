package com.example.weatherapp.network

import com.example.weatherapp.network.model.single_city_forecast_data.SimpleForecastForSingleCity
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("weather")
    suspend fun getForecastByCityName(
        @Query("q") cityName: String
    ) : SimpleForecastForSingleCity

    @GET("weather")
    suspend fun getForecastByGeographicCoordinates(
        @Query("lat") latitude: Int,
        @Query("lon") longitude: Int
    ) : SimpleForecastForSingleCity


}
