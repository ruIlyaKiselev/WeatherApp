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
import com.example.weatherapp.domain.Forecast
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
                Log.d("MyLog", viewModel.singleForecast.value.toString())
            }
        }

        viewModel.singleForecast.observe(viewLifecycleOwner) {
            bindSimpleForecast(it)
        }

        return view
    }

    private fun bindSimpleForecast(forecast: Forecast) {
        binding.cityName.text = forecast.geography?.city.toString()
        binding.currentTemperature.text = forecast.temperature?.temp?.roundToInt().toString() + "°"
        binding.minTemperature.text = forecast.temperature?.tempMin?.roundToInt().toString() + "°"
        binding.maxTemperature.text = forecast.temperature?.tempMax?.roundToInt().toString() + "°"
        binding.feelsTemperature.text = forecast.temperature?.tempFeelsLike?.roundToInt().toString() + "°"
        binding.weatherDescription.text = forecast.description?.title
        binding.sunImageView.setImageResource(
            WeatherCodeConverter.getResourceCode(forecast.description?.icon.toString())
        )
    }

}