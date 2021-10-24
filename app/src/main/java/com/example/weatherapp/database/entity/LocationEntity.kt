package com.example.weatherapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.weatherapp.database.RoomContract

@Entity(
    tableName = RoomContract.Location.TABLE_NAME,
    indices = [Index(RoomContract.Location.COLUMN_NAME_ID)]
)
data class LocationEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RoomContract.Location.COLUMN_NAME_ID)
    val _id: Int = 0,
    @ColumnInfo(name = RoomContract.Location.COLUMN_NAME_LATITUDE)
    val latitude: Double? = 0.0,
    @ColumnInfo(name = RoomContract.Location.COLUMN_NAME_LONGITUDE)
    val longitude: Double? = 0.0,
    @ColumnInfo(name = RoomContract.Location.COLUMN_NAME_REGION)
    val region: String? = "",
    @ColumnInfo(name = RoomContract.Location.COLUMN_NAME_LAST_UPDATE)
    val lastUpdate: String? = ""
)