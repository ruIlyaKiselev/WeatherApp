package com.example.weatherapp.network.mapbox.model


import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("id")
    val id: String?,
    @SerializedName("short_code")
    val shortCode: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("wikidata")
    val wikidata: String?
)