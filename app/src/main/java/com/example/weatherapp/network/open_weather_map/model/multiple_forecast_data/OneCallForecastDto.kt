package com.example.weatherapp.network.open_weather_map.model.multiple_forecast_data


import com.google.gson.annotations.SerializedName

data class OneCallForecastDto(
    @SerializedName("current")
    val current: CurrentDto?,
    @SerializedName("daily")
    val daily: List<DailyDto>?,
    @SerializedName("hourly")
    val hourly: List<HourlyDto>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("minutely")
    val minutely: List<MinutelyDto>?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
)