package com.example.weatherapp.repository

import com.example.weatherapp.domain.Converters.Companion.toDomain
import com.example.weatherapp.domain.Place
import com.example.weatherapp.network.mapbox.MapboxContract
import com.example.weatherapp.network.mapbox.MapboxService

class PlacesRepositoryImpl(
    private val mapboxService: MapboxService
): PlacesRepository {

    override suspend fun loadPlacesBySubstring(substring: String): List<Place> =
        mapboxService
            .getPlacesBySubstring(
                placeSubstring = substring,
                limit = MapboxContract.LIMIT,
                types = MapboxContract.TYPES
            ).toDomain()
}