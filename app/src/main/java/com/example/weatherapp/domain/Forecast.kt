package com.example.weatherapp.domain

import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto

data class Forecast (
    val geography: Geography?,
    val temperature: Temperature?,
    val pressure: Int?,
    val humidity: Int?,
    val sunInfo: SunInfo?,
    val description: Description?,
    val windInfo: WindInfo?
)



