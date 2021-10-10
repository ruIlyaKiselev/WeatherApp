package com.example.weatherapp.network.open_weather_map.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class FeelsLikeDto(
    @SerializedName("day")
    val day: Double?,
    @SerializedName("eve")
    val eve: Double?,
    @SerializedName("morn")
    val morn: Double?,
    @SerializedName("night")
    val night: Double?
)