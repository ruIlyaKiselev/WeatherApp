package com.example.weatherapp.network.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?
)