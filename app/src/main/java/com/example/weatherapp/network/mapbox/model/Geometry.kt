package com.example.weatherapp.network.mapbox.model


import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("coordinates")
    val coordinates: List<Double>?,
    @SerializedName("type")
    val type: String?
)