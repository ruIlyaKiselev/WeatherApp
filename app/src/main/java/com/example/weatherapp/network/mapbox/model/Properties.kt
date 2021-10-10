package com.example.weatherapp.network.mapbox.model


import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("wikidata")
    val wikidata: String?
)