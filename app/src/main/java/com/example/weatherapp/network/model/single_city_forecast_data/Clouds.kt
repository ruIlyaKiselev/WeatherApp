package com.example.weatherapp.network.model.single_city_forecast_data


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)