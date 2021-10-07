package com.example.weatherapp.domain

data class SimpleForecast (
    val geography: Geography?,
    val temperature: Temperature?,
    val pressure: Int?,
    val humidity: Int?,
    val sunInfo: SunInfo?,
    val description: Description?,
    val windInfo: WindInfo?,
    val date: Int?
)



