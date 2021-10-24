package com.example.weatherapp.di

import com.example.weatherapp.database.WeatherAppDatabase
import com.example.weatherapp.network.mapbox.MapboxService
import com.example.weatherapp.network.open_weather_map.OpenWeatherMapService
import com.example.weatherapp.repository.PlacesRepository
import com.example.weatherapp.repository.PlacesRepositoryImpl
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        openWeatherMapService: OpenWeatherMapService,
        database: WeatherAppDatabase
    ): WeatherRepository {
        return WeatherRepositoryImpl(openWeatherMapService, database)
    }

    @Singleton
    @Provides
    fun providePlacesRepository(
        mapboxService: MapboxService
    ): PlacesRepository {
        return PlacesRepositoryImpl(mapboxService)
    }
}