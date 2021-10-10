package com.example.weatherapp.network.open_weather_map.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all")
    val all: Int?
)