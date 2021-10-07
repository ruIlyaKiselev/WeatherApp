package com.example.weatherapp.network.model.simple_forecast_data


import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all")
    val all: Int?
)