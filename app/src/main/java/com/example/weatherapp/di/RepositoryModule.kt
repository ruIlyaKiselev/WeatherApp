package com.example.weatherapp.di

import com.example.weatherapp.network.OpenWeatherMapService
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
        openWeatherMapService: OpenWeatherMapService
    ): WeatherRepository {
        return WeatherRepositoryImpl(openWeatherMapService)
    }
}