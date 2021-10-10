package com.example.weatherapp.network.mapbox.model


import com.google.gson.annotations.SerializedName

data class PlacesDTO(
    @SerializedName("attribution")
    val attribution: String?,
    @SerializedName("features")
    val features: List<Feature>?,
    @SerializedName("query")
    val query: List<String>?,
    @SerializedName("type")
    val type: String?
)