package com.example.weatherapp.network.open_weather_map.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?
)