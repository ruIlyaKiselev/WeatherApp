package com.example.weatherapp.ui.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.BaseApplication
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import com.example.weatherapp.domain.SimpleForecast
import com.example.weatherapp.network.WeatherCodeConverter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainScreenFragment: Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()
    private lateinit var binding: FragmentMainScreenBinding

    @Inject lateinit var baseApplication: BaseApplication

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

        baseApplication.location.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.loadSimpleForecastByGeographicCoordinates(it.latitude, it.longitude)
                Log.d("MyLog", it.latitude.toString() + " " + it.longitude)
                Log.d("MyLog", viewModel.singleSimpleForecast.value.toString())
            }
        }

        viewModel.singleSimpleForecast.observe(viewLifecycleOwner) {
            bindSimpleForecast(it)
        }

        return view
    }

    private fun bindSimpleForecast(simpleForecast: SimpleForecast) {
        binding.cityName.text = simpleForecast.geography?.city.toString()
        binding.currentTemperature.text = simpleForecast.temperature?.temp?.roundToInt().toString() + "°"
        binding.minTemperature.text = simpleForecast.temperature?.tempMin?.roundToInt().toString() + "°"
        binding.maxTemperature.text = simpleForecast.temperature?.tempMax?.roundToInt().toString() + "°"
        binding.feelsTemperature.text = simpleForecast.temperature?.tempFeelsLike?.roundToInt().toString() + "°"
        binding.weatherDescription.text = simpleForecast.description?.title
        binding.sunImageView.setImageResource(
            WeatherCodeConverter.getResourceCode(simpleForecast.description?.icon.toString())
        )
    }

}