package com.example.weatherapp.repository

import com.example.weatherapp.domain.Converters.Companion.toDomain
import com.example.weatherapp.domain.Forecast
import com.example.weatherapp.network.OpenWeatherMapContract
import com.example.weatherapp.network.OpenWeatherMapService
import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto

class WeatherRepositoryImpl(
    private val openWeatherMapService: OpenWeatherMapService
): WeatherRepository {

    override suspend fun loadSimpleForecastByCityName(
        cityName: String
    ): Forecast = openWeatherMapService.getSimpleForecastByCityName(
            cityName,
            OpenWeatherMapContract.UNITS
        ).toDomain()

    override suspend fun loadSimpleForecastByGeographicCoordinates(
        latitude: Double,
        longitude: Double
    ): Forecast = openWeatherMapService.getSimpleForecastByGeographicCoordinates(
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
    )

}