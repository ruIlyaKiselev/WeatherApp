package com.example.weatherapp.network.mapbox.model


import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("bbox")
    val bbox: List<Double>?,
    @SerializedName("center")
    val center: List<Double>?,
    @SerializedName("context")
    val context: List<Context>?,
    @SerializedName("geometry")
    val geometry: Geometry?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("place_name")
    val placeName: String?,
    @SerializedName("place_type")
    val placeType: List<String>?,
    @SerializedName("properties")
    val properties: Properties?,
    @SerializedName("relevance")
    val relevance: Double?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)