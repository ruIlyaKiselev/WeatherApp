package com.example.weatherapp.network.open_weather_map.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?
)