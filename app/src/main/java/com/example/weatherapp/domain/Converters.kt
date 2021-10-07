package com.example.weatherapp.domain

import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto

class Converters {
    companion object {

        fun SimpleForecastDto.toDomain(): Forecast {
            return Forecast(
                Geography(
                    city = this.name,
                    country = this.sys?.country,
                    lat = this.coord?.lat,
                    lon = this.coord?.lon
                ),
                Temperature(
                    temp = this.main?.temp,
                    tempMax = this.main?.tempMax,
                    tempMin = this.main?.tempMin,
                    tempFeelsLike = this.main?.feelsLike
                ),
                pressure = this.main?.pressure,
                humidity = this.main?.humidity,
                sunInfo = SunInfo(
                    sunrise = this.sys?.sunrise,
                    sunset = this.sys?.sunset
                ),
                description = Description(
                    title = this.weather?.get(0)?.description,
                    icon = this.weather?.get(0)?.icon,
                    id = this.weather?.get(0)?.id
                ),
                windInfo = WindInfo(
                    windDeg = this.wind?.deg,
                    windGust = this.wind?.gust,
                    windSpeed = this.wind?.speed
                )
            )
        }
    }
}