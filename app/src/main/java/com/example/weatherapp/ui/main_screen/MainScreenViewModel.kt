package com.example.weatherapp.ui.main_screen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BaseApplication
import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.single_forecast_data.SimpleForecast
import com.example.weatherapp.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val baseApplication: BaseApplication
): ViewModel() {
    private var mutableSingleForecast = MutableLiveData<SimpleForecast>()
    val singleForecast: LiveData<SimpleForecast> = mutableSingleForecast

    private var mutableOneCallForecast = MutableLiveData<OneCallForecast>()
    val oneCallForecastForecast: LiveData<OneCallForecast> = mutableOneCallForecast

    private var mutableLocation = MutableLiveData<Location>()
    val location: LiveData<Location> = mutableLocation



    fun loadSimpleForecastByCityName(cityName: String) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableSingleForecast.postValue(
                repository.loadSimpleForecastByCityName(cityName)
            )
        }
    }

    fun loadSimpleForecastByGeographicCoordinates(latitude: Double, longitude: Double) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableSingleForecast.postValue(
                repository.loadSimpleForecastByGeographicCoordinates(latitude, longitude)
            )
        }
    }

    fun loadOneCallForecastByGeographicCoordinates(latitude: Double, longitude: Double) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableOneCallForecast.postValue(
                repository.loadOneCallForecastByGeographicCoordinates(latitude, longitude)
            )
        }
    }
}