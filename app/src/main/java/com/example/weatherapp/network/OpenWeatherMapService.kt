package com.example.weatherapp.network

import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecastDto
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("weather")
    suspend fun getSimpleForecastByCityName(
        @Query("q") cityName: String,
        @Query("units") units: String
    ) : SimpleForecastDto

    @GET("weather")
    suspend fun getSimpleForecastByGeographicCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String
    ) : SimpleForecastDto

    @GET("onecall")
    suspend fun getOneCallForecastByGeographicCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String
    ) : OneCallForecastDto
}
