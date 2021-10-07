package com.example.weatherapp

import android.app.Application
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    private var mutableLocation = MutableLiveData<Location>()
    var location: LiveData<Location> = mutableLocation

    fun setLocation(location: Location) {
        mutableLocation.postValue(location)
    }
}