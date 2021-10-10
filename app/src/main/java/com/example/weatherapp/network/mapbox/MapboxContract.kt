package com.example.weatherapp.network.mapbox

class MapboxContract {
    companion object {
        const val BASE_URL = "https://api.mapbox.com/geocoding/v5/"
        const val API_TOKEN = "pk.eyJ1IjoicnVpbHlha2lzZWxldiIsImEiOiJja3VreTJ4enIwNGR5Mm50aDE2bGhkOXJiIn0.uHI91m20aYWKtf2TMA8AIA"
        const val LIMIT = 5;
        const val TYPES = "place"
        const val UNITS = "metric"
    }
}