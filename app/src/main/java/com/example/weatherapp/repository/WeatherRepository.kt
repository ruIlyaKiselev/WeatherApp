package com.example.weatherapp.repository

import com.example.weatherapp.domain.Forecast
import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto

interface WeatherRepository {
    suspend fun loadSimpleForecastByCityName(cityName: String): Forecast
    suspend fun loadSimpleForecastByGeographicCoordinates(latitude: Double, longitude: Double): Forecast
    suspend fun loadOneCallForecastByGeographicCoordinates(latitude: Double, longitude: Double): OneCallForecast
}