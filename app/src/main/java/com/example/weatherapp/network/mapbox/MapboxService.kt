package com.example.weatherapp.network.mapbox

import com.example.weatherapp.network.mapbox.model.PlacesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MapboxService {
    @GET("mapbox.places/{placeSubstring}.json")
    suspend fun getPlacesBySubstring(
        @Path("placeSubstring") placeSubstring: String,
        @Query("limit") limit: Int,
        @Query("types") types: String
    ): PlacesDTO
}