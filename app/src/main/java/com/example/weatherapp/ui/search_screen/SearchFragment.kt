package com.example.weatherapp.ui.search_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.domain.Forecast
import com.example.weatherapp.network.WeatherCodeConverter
import com.example.weatherapp.network.model.simple_forecast_data.SimpleForecastDto
import com.jakewharton.rxbinding4.widget.textChangeEvents
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchFragmentViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_search,
            container,
            false
        )

        binding.lifecycleOwner = this

        val view: View = binding.root

        binding.viewModel = viewModel


        binding.cityNameEditText.textChangeEvents()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.loadSimpleForecastByCityName(it.text.toString())
            }, {
                Log.e("MyLog","CoroutineExceptionHandler got ${it.localizedMessage}")
            })

        viewModel.singleForecast.observe(viewLifecycleOwner) {
            bindSimpleForecast(it)
        }

        return view
    }

    private fun bindSimpleForecast(forecast: Forecast) {
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