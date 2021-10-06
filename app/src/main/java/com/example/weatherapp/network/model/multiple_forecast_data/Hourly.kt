package com.example.weatherapp.network.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("clouds")
    val clouds: Int?,
    @SerializedName("dew_point")
    val dewPoint: Double?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pop")
    val pop: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("uvi")
    val uvi: Int?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: List<WeatherXX>?,
    @SerializedName("wind_deg")
    val windDeg: Int?,
    @SerializedName("wind_gust")
    val windGust: Double?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)