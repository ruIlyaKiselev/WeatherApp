package com.example.weatherapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.weatherapp.database.RoomContract
import com.example.weatherapp.domain.OneCallForecast

@Entity(
    tableName = RoomContract.OneCallForecast.TABLE_NAME,
    indices = [Index(RoomContract.OneCallForecast.COLUMN_NAME_ID)]
)
data class OneCallForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomContract.OneCallForecast.COLUMN_NAME_ID)
    val _id: Int = 0,
    @ColumnInfo(name = RoomContract.OneCallForecast.COLUMN_NAME_DATA)
    val data: OneCallForecast?,
    @ColumnInfo(name = RoomContract.OneCallForecast.COLUMN_NAME_LAST_UPDATE)
    val lastUpdate: String?
)
