package com.example.weatherapp.ui.map_screen

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.BaseApplication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val baseApplication: BaseApplication
): ViewModel() {

    private var mutableCurrentMap: MutableLiveData<GoogleMap?> = MutableLiveData()
    var currentMap: LiveData<GoogleMap?> = mutableCurrentMap

    private var mutableCurrentLocation: MutableLiveData<Location?> = MutableLiveData()
    var currentLocation: LiveData<Location?> = mutableCurrentLocation

    private var mutableLastLocation: MutableLiveData<Location?> = MutableLiveData()
    var lastLocation: LiveData<Location?> = mutableLastLocation

    private var mutableCurrentLocationMarker: MutableLiveData<Marker?> = MutableLiveData()
    var currentLocationMarker: LiveData<Marker?> = mutableCurrentLocationMarker

    init {
        mutableCurrentLocation.postValue(baseApplication.location.value)
        mutableLastLocation.postValue(Location(baseApplication.location.value))
    }

    fun configureMap(googleMap: GoogleMap) {
        mutableCurrentMap.postValue(googleMap)

        if (lastLocation.value != null) {
            val currentPosition = LatLng(lastLocation.value!!.latitude, lastLocation.value!!.longitude)
            val markerOptions = MarkerOptions()

            markerOptions.apply {
                this.position(currentPosition)
                this.title("${currentPosition.latitude} : ${currentPosition.longitude}")
//                moveMarkerTo(currentPosition.latitude, currentPosition.longitude)
            }

            currentMap.apply {
                this.value?.clear()
                this.value?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 10f))
                this.value?.addMarker(markerOptions)
            }
        }

        googleMap.setOnMapClickListener { coords ->
            moveMarkerTo(coords.latitude, coords.longitude)
        }
    }

    fun searchLocation(locationName: String) {

        var addressList: List<Address>? = null

        currentMap.value?.clear()

        if (locationName == "") {
            Toast.makeText(baseApplication, "provide location", Toast.LENGTH_SHORT).show()
        } else {
            val geoCoder = Geocoder(baseApplication)

            try {
                addressList = geoCoder.getFromLocationName(locationName, 1)
            } catch (e: IOException){
                e.printStackTrace()
            }

            val address = addressList?.get(0)
            if (address != null) {
                moveMarkerTo(address.latitude, address.longitude)
            }
        }
    }

    fun moveMarkerTo(latitude: Double, longitude: Double) {
        val latLng = LatLng(latitude, longitude)
        mutableCurrentMap.value?.clear()
        currentMap.value!!.addMarker(MarkerOptions()
            .position(latLng)
            .title("$latitude : $longitude"))
        currentMap.value!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        lastLocation.value?.latitude = latitude
        lastLocation.value?.longitude = longitude
    }
}