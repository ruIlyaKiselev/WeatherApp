package com.example.weatherapp.ui.search_screen

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.weatherapp.BaseApplication
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapsBinding
    @Inject lateinit var baseApplication: BaseApplication

    private var currentMap: GoogleMap? = null
    private var lastLocation: Location? = null
    private var currentLocationMarker: Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_maps,
            container,
            false
        )

        binding.lifecycleOwner = this

        val view: View = binding.root

        binding.searchPlaceButton.setOnClickListener {
            searchLocation()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        currentMap = googleMap
        lastLocation = baseApplication.location.value

        if (lastLocation != null) {
            val currentPosition = LatLng(lastLocation!!.latitude, lastLocation!!.longitude)
            val markerOptions = MarkerOptions()

            markerOptions.apply {
                this.position(currentPosition)
                this.title("${currentPosition.latitude} : ${currentPosition.longitude}")
                currentLocationMarker = currentMap!!.addMarker(this)
            }

            currentMap.apply {
                this?.clear()
                this?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 10f))
                this?.addMarker(markerOptions)
            }
        }
    }

    private fun searchLocation() {
        val locationSearch: EditText = binding.searchPlaceTextView
        val location: String = locationSearch.text.toString().trim()
        var addressList: List<Address>? = null

        if (location == "") {
            Toast.makeText(baseApplication, "provide location", Toast.LENGTH_SHORT).show()
        } else {
            val geoCoder = Geocoder(baseApplication)

            try {
                addressList = geoCoder.getFromLocationName(location, 1)
            } catch (e: IOException){
                e.printStackTrace()
            }

            val address = addressList?.get(0)
            if (address != null) {
                val latLng = LatLng(address.latitude, address.longitude)
                currentMap!!.addMarker(MarkerOptions().position(latLng).title(location))
                currentMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            }
        }
    }
}