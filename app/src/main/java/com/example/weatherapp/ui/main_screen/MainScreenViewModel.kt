package com.example.weatherapp.ui.main_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.OpenWeatherMapService
import com.example.weatherapp.network.model.single_city_forecast_data.SimpleForecastForSingleCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val openWeatherMapService: OpenWeatherMapService
): ViewModel() {
    private var mutableTrackedForecast = MutableLiveData<SimpleForecastForSingleCity>()
    val trackedForecast: LiveData<SimpleForecastForSingleCity> = mutableTrackedForecast

    fun loadForecastByCityName(cityName: String) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableTrackedForecast.postValue(
                openWeatherMapService.getForecastByCityName(cityName)
            )
        }
    }

    fun loadForecastByGeographicCoordinates(latitude: Int, longitude: Int) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableTrackedForecast.postValue(
                openWeatherMapService.getForecastByGeographicCoordinates(latitude, longitude)
            )
        }
    }
}