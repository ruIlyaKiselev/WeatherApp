package com.example.weatherapp.network.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)