package com.example.weatherapp.repository

import com.example.weatherapp.domain.Converters.Companion.toDomain
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.domain.SimpleForecast
import com.example.weatherapp.network.open_weather_map.OpenWeatherMapContract
import com.example.weatherapp.network.open_weather_map.OpenWeatherMapService

class WeatherRepositoryImpl(
    private val openWeatherMapService: OpenWeatherMapService
): WeatherRepository {

    override suspend fun loadSimpleForecastByCityName(
        cityName: String
    ): SimpleForecast = openWeatherMapService.getSimpleForecastByCityName(
            cityName,
            OpenWeatherMapContract.UNITS
        ).toDomain()

    override suspend fun loadSimpleForecastByGeographicCoordinates(
        latitude: Double,
        longitude: Double
    ): SimpleForecast = openWeatherMapService.getSimpleForecastByGeographicCoordinates(
            latitude,
            longitude,
            OpenWeatherMapContract.UNITS
        ).toDomain()

    override suspend fun loadOneCallForecastByGeographicCoordinates(
        latitude: Double,
        longitude: Double
    ): OneCallForecast = openWeatherMapService.getOneCallForecastByGeographicCoordinates(
        latitude,
        longitude,
        OpenWeatherMapContract.UNITS
    ).toDomain()

}