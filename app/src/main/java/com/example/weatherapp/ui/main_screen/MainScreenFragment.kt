package com.example.weatherapp.ui.main_screen

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_main_screen,
            container,
            false
        )

        binding.lifecycleOwner = this

        val view: View = binding.root

        binding.button.setOnClickListener {
            fetchLocationPermissions()
            viewModel.getGeolocation()

            viewModel.location.observe(viewLifecycleOwner) {
                viewModel.loadSimpleForecastByGeographicCoordinates(it.latitude, it.longitude)
                Log.d("MyLog", viewModel.singleForecast.toString())
            }
        }

        return view
    }

    private fun fetchLocationPermissions() {
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        val coarseLocation = ActivityCompat.checkSelfPermission(
            requireActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (fineLocationPermission != PackageManager.PERMISSION_GRANTED
            && coarseLocation != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
        }
    }
}