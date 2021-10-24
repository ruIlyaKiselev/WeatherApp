package com.example.weatherapp.ui.stored_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BaseApplication
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoredViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val baseApplication: BaseApplication
): ViewModel() {

    private var mutableOneCallForecastList = MutableLiveData<OneCallForecast>()
    val oneCallForecastList: LiveData<OneCallForecast> = mutableOneCallForecastList

    fun loadOneCallForecastByGeographicCoordinates(latitude: Double, longitude: Double) {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("MyLog","CoroutineExceptionHandler got $exception")
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            mutableOneCallForecastList.postValue(
                repository.loadOneCallForecastByGeographicCoordinates(latitude, longitude)
            )
        }
    }
}