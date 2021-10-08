package com.example.weatherapp.ui.map_screen

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MapsViewModel by viewModels()
    private lateinit var binding: FragmentMapsBinding

    private var lastLocation: Location? = null
    private var currentLocation: Location? = null

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

        binding.currentCoordinateFAB.setOnClickListener {
            viewModel.moveMarkerTo(currentLocation!!.latitude, currentLocation!!.longitude)
        }

        binding.selectCoordinateFAB.setOnClickListener {
            val bundle = bundleOf("location" to lastLocation)
            Log.d("MyLog", lastLocation.toString())
            findNavController().navigate(R.id.from_maps_to_search_action, bundle)
        }

        viewModel.lastLocation.observe(viewLifecycleOwner) {
            lastLocation = it
        }

        viewModel.currentLocation.observe(viewLifecycleOwner) {
            currentLocation = it
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        viewModel.configureMap(googleMap)
    }

    private fun searchLocation() {
        val locationSearch: EditText = binding.searchPlaceTextView
        val location: String = locationSearch.text.toString().trim()
        viewModel.searchLocation(location)
    }
}