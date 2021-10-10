package com.example.weatherapp.domain

import com.example.weatherapp.network.mapbox.model.PlacesDTO
import com.example.weatherapp.network.open_weather_map.model.multiple_forecast_data.OneCallForecastDto
import com.example.weatherapp.network.open_weather_map.model.simple_forecast_data.SimpleForecastDto

class Converters {
    companion object {

        fun SimpleForecastDto.toDomain(): SimpleForecast = SimpleForecast(
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
            ),
            date = this.dt
        )

        fun OneCallForecastDto.toDomain(): OneCallForecast = OneCallForecast(
            simpleForecast = SimpleForecast(
                Geography(
                    city = this.timezone,
                    country = this.timezone,
                    lat = this.lat,
                    lon = this.lon
                ),
                Temperature(
                    temp = this.current?.temp,
                    tempMax = this.daily?.get(0)?.temp?.max,
                    tempMin = this.daily?.get(0)?.temp?.min,
                    tempFeelsLike = this.current?.feelsLike
                ),
                pressure = this.current?.pressure,
                humidity = this.current?.humidity,
                sunInfo = SunInfo(
                    sunrise = this.current?.sunrise,
                    sunset = this.current?.sunset
                ),
                description = Description(
                    title = this.current?.weather?.get(0)?.description,
                    icon = this.current?.weather?.get(0)?.icon,
                    id = this.current?.weather?.get(0)?.id,
                ),
                windInfo = WindInfo(
                    windDeg = this.daily?.get(0)?.windDeg,
                    windGust = this.daily?.get(0)?.windGust,
                    windSpeed = this.daily?.get(0)?.windSpeed,
                ),
                date = this.current?.dt
            ),
            hourly = this.hourly?.map {
                HourlyItem(
                    time = it.dt.toString(),
                    icon = it.weather?.get(0)?.icon,
                    id = it.weather?.get(0)?.id,
                    temp = it.temp,
                    description = it.weather?.get(0)?.description
                )
            },
            daily = this.daily?.map {
                DailyItem(
                    dayOfWeek = it.dt.toString(),
                    date = it.dt.toString(),
                    icon = it.weather?.get(0)?.icon,
                    id = it.weather?.get(0)?.id,
                    temp = it.temp?.day,
                    tempMin = it.temp?.min,
                    tempMax = it.temp?.max,
                    tempFeelLike = it.feelsLike?.day,
                    description = it.weather?.get(0)?.description
                )
            }
        )

        fun PlacesDTO.toDomain(): List<Place> = this.features?.map {
            Place(
                text = it.text,
                placeName = it.placeName,
                lan = it.center?.get(0),
                lon = it.center?.get(1)
            )
        } ?: emptyList()
    }
}