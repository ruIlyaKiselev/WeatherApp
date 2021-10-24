package com.example.weatherapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.weatherapp.database.RoomContract
import com.example.weatherapp.domain.SimpleForecast

@Entity(
    tableName = RoomContract.SimpleForecast.TABLE_NAME,
    indices = [Index(RoomContract.SimpleForecast.COLUMN_NAME_ID)]
)
data class SimpleForecastEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomContract.SimpleForecast.COLUMN_NAME_ID)
    val _id: Int = 0,
    @ColumnInfo(name = RoomContract.SimpleForecast.COLUMN_NAME_DATA)
    val data: SimpleForecast?,
    @ColumnInfo(name = RoomContract.SimpleForecast.COLUMN_NAME_LAST_UPDATE)
    val lastUpdate: String?
)