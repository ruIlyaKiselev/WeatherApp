package com.example.weatherapp.database

import android.provider.BaseColumns

object RoomContract {
    const val DATABASE_NAME = "WeatherApp.db"

    object SimpleForecast {
        const val TABLE_NAME = "SimpleForecast"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_DATA = "Data"
        const val COLUMN_NAME_LAST_UPDATE = "LastUpdate"
    }

    object OneCallForecast {
        const val TABLE_NAME = "OneCallForecast"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_DATA = "Data"
        const val COLUMN_NAME_LAST_UPDATE = "LastUpdate"
    }

    object Location {
        const val TABLE_NAME = "Location"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_LATITUDE = "Latitude"
        const val COLUMN_NAME_LONGITUDE = "Longitude"
        const val COLUMN_NAME_REGION = "Region"
        const val COLUMN_NAME_LAST_UPDATE = "LastUpdate"
    }
}