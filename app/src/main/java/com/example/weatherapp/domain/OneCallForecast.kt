package com.example.weatherapp.domain

data class OneCallForecast(
    val simpleForecast: SimpleForecast?,
    val hourly: List<HourlyItem>?,
    val daily: List<DailyItem>?
)