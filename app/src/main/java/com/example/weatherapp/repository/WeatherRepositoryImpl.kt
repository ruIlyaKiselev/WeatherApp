package com.example.weatherapp.repository

import com.example.weatherapp.network.OpenWeatherMapContract
import com.example.weatherapp.network.OpenWeatherMapService
import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecast

class WeatherRepositoryImpl(
    private val openWeatherMapService: OpenWeatherMapService
): WeatherRepository {

    override suspend fun loadSimpleForecastByCityName(
        cityName: String
    ): SimpleForecast = openWeatherMapService.getSimpleForecastByCityName(
        cityName,
        OpenWeatherMapContract.UNITS
    )

    override suspend fun loadSimpleForecastByGeographicCoordinates(
        latitude: Double,
        longitude: Double
    ): SimpleForecast = openWeatherMapService.getSimpleForecastByGeographicCoordinates(
        latitude,
        longitude,
        OpenWeatherMapContract.UNITS
    )


    override suspend fun loadOneCallForecastByGeographicCoordinates(
        latitude: Double,
        longitude: Double
    ): OneCallForecast = openWeatherMapService.getOneCallForecastByGeographicCoordinates(
        latitude,
        longitude,
        OpenWeatherMapContract.UNITS
    )

}