package com.example.weatherapp.repository

import com.example.weatherapp.domain.Place

interface PlacesRepository {
    suspend fun loadPlacesBySubstring(substring: String): List<Place>
}