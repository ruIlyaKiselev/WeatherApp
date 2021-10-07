package com.example.weatherapp.network.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class WeatherXXDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?
)