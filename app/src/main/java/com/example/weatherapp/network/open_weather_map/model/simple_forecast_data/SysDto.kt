package com.example.weatherapp.network.open_weather_map.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class SysDto(
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("type")
    val type: Int?
)