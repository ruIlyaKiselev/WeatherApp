package com.example.weatherapp.domain

data class DailyItem (
    val dayOfWeek: String?,
    val date: String?,
    val icon: String?,
    val id: Int?,
    val temp: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val tempFeelLike: Double?,
    val description: String?
)