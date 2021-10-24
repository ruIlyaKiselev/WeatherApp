package com.example.weatherapp.di

import com.example.weatherapp.BaseApplication
import com.example.weatherapp.database.WeatherAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideWeatherRoomDatabase(
        baseApplication: BaseApplication
    ): WeatherAppDatabase {
        return WeatherAppDatabase.create(baseApplication)
    }
}