package com.example.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.database.entity.OneCallForecastEntity
import com.example.weatherapp.database.entity.SimpleForecastEntity

@Dao
interface WeatherDao {

    @Query("SELECT * from SimpleForecast")
    suspend fun getAllSimpleForecast(): List<SimpleForecastEntity>

    @Query("SELECT * from OneCallForecast")
    suspend fun getAllOneCallForecast(): List<OneCallForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimpleForecast(simpleForecast: SimpleForecastEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimpleForecasts(simpleForecasts: List<SimpleForecastEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneCallForecast(oneCallForecast: OneCallForecastEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneCallForecasts(oneCallForecasts: List<OneCallForecastEntity>)

    @Query("DELETE FROM SimpleForecast")
    suspend fun deleteAllSimpleForecast()

    @Query("DELETE FROM OneCallForecast")
    suspend fun deleteAllOneCallForecast()
}