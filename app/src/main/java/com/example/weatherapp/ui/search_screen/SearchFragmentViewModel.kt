package com.example.weatherapp.ui.search_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.Forecast
import com.example.weatherapp.network.model.multiple_forecast_data.OneCallForecast
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    private var mutableSingleForecast = MutableLiveData<Forecast>()
    val singleForecast: LiveData<Forecast> = mutableSingleForecast

    private var mutableOneCallForecast = MutableLiveData<OneCallForecast>()
    val oneCallForecastForecast: LiveData<OneCallForecast> = mutableOneCallForecast

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