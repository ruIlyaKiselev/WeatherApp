package com.example.weatherapp.domain

data class HourlyItem(
    val time: String?,
    val icon: String?,
    val id: Int?,
    val temp: Double?,
    val description: String?
)
