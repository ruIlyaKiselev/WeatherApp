package com.example.weatherapp.repository

import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecast

interface WeatherRepository {
    suspend fun loadSimpleForecastByCityName(cityName: String): SimpleForecast
    suspend fun loadSimpleForecastByGeographicCoordinates(latitude: Double, longitude: Double): SimpleForecast
    suspend fun loadOneCallForecastByGeographicCoordinates(latitude: Double, longitude: Double): OneCallForecast
}