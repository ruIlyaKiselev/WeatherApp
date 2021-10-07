package com.example.weatherapp.network

import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("weather")
    suspend fun getSimpleForecastByCityName(
        @Query("q") cityName: String,
        @Query("units") units: String
    ) : SimpleForecast

    @GET("weather")
    suspend fun getSimpleForecastByGeographicCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String
    ) : SimpleForecast

    @GET("onecall")
    suspend fun getOneCallForecastByGeographicCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String
    ) : OneCallForecast
}
