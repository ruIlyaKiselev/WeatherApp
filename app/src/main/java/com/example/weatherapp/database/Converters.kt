package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.domain.SimpleForecast
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun simpleForecastToJson(value: SimpleForecast?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToSimpleForecast(value: String) = Gson().fromJson(value, SimpleForecast::class.java)

    @TypeConverter
    fun oneCallForecastToJson(value: OneCallForecast?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToOneCallForecast(value: String) = Gson().fromJson(value, OneCallForecast::class.java)
}