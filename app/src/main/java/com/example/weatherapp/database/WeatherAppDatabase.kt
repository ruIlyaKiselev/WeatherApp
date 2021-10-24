package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.database.entity.OneCallForecastEntity
import com.example.weatherapp.database.entity.SimpleForecastEntity
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [
        SimpleForecastEntity::class,
        OneCallForecastEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherAppDatabase: RoomDatabase() {
    abstract val dao: WeatherDao

    companion object {
        fun create(applicationContext: Context): WeatherAppDatabase {

            return Room.databaseBuilder(
                applicationContext,
                WeatherAppDatabase::class.java,
                RoomContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}