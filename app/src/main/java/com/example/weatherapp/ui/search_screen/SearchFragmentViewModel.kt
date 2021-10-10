package com.example.weatherapp.ui.search_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.domain.Place
import com.example.weatherapp.domain.SimpleForecast
import com.example.weatherapp.repository.PlacesRepository
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val placesRepository: PlacesRepository

    ): ViewModel() {
    private var mutableSingleForecast = MutableLiveData<SimpleForecast>()
    val singleSimpleForecast: LiveData<SimpleForecast> = mutableSingleForecast

    private var mutableOneCallForecast = MutableLiveData<OneCallForecast>()
    val oneCallForecastForecast: LiveData<OneCallForecast> = mutableOneCallForecast

    private var mutablePlaces = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = mutablePlaces

    fun loadSimpleForecastByCityName(cityName: String) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableSingleForecast.postValue(
                weatherRepository.loadSimpleForecastByCityName(cityName)
            )
        }
    }

    fun loadSimpleForecastByGeographicCoordinates(latitude: Double, longitude: Double) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableSingleForecast.postValue(
                weatherRepository.loadSimpleForecastByGeographicCoordinates(latitude, longitude)
            )
        }
    }

    fun loadOneCallForecastByGeographicCoordinates(latitude: Double, longitude: Double) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableOneCallForecast.postValue(
                weatherRepository.loadOneCallForecastByGeographicCoordinates(latitude, longitude)
            )
        }
    }

    fun loadPlacesBySubstring(substring: String) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutablePlaces.postValue(
                placesRepository.loadPlacesBySubstring(substring)
            )
        }
    }
}