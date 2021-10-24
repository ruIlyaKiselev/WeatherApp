package com.example.weatherapp.repository

import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.domain.SimpleForecast

interface WeatherRepository {
    suspend fun loadSimpleForecastByCityName(cityName: String): SimpleForecast
    suspend fun loadSimpleForecastByGeographicCoordinates(latitude: Double, longitude: Double): SimpleForecast
    suspend fun loadOneCallForecastByGeographicCoordinates(latitude: Double, longitude: Double): OneCallForecast


}