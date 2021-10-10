package com.example.weatherapp.network.open_weather_map.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class MinutelyDto(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("precipitation")
    val precipitation: Int?
)