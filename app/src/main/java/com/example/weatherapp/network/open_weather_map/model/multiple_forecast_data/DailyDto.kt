package com.example.weatherapp.network.open_weather_map.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class DailyDto(
    @SerializedName("clouds")
    val clouds: Int?,
    @SerializedName("dew_point")
    val dewPoint: Double?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("feels_like")
    val feelsLike: FeelsLikeDto?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("moon_phase")
    val moonPhase: Double?,
    @SerializedName("moonrise")
    val moonrise: Int?,
    @SerializedName("moonset")
    val moonset: Int?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("rain")
    val rain: Double?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("temp")
    val temp: TempDto?,
    @SerializedName("uvi")
    val uvi: Double?,
    @SerializedName("weather")
    val weather: List<WeatherXDto>?,
    @SerializedName("wind_deg")
    val windDeg: Int?,
    @SerializedName("wind_gust")
    val windGust: Double?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)