package com.example.weatherapp.network.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?
)