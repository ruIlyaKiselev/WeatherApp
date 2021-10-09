package com.example.weatherapp.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.BaseApplication
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.domain.SimpleForecast
import com.example.weatherapp.network.WeatherCodeConverter
import com.example.weatherapp.ui.daily_recycler_view.DailyItemAdapter
import com.example.weatherapp.ui.hourly_recycler_view.HourlyItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainScreenFragment: Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()
    private lateinit var binding: FragmentMainScreenBinding

    private lateinit var hourlyRecyclerViewAdapter: HourlyItemAdapter
    private lateinit var dailyRecyclerViewAdapter: DailyItemAdapter

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

        hourlyRecyclerViewAdapter = HourlyItemAdapter()

        val hourlyLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.mainScreenHourlyRecyclerView.layoutManager = hourlyLayoutManager
        binding.mainScreenHourlyRecyclerView.adapter = hourlyRecyclerViewAdapter

        dailyRecyclerViewAdapter = DailyItemAdapter()

        val dailyLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.mainScreenDailyRecyclerView.layoutManager = dailyLayoutManager
        binding.mainScreenDailyRecyclerView.adapter = dailyRecyclerViewAdapter

        baseApplication.location.observe(viewLifecycleOwner) {
            if (it != null) {
//                viewModel.loadSimpleForecastByGeographicCoordinates(it.latitude, it.longitude)
                viewModel.loadOneCallForecastByGeographicCoordinates(it.latitude, it.longitude)
            }
        }

        viewModel.singleSimpleForecast.observe(viewLifecycleOwner) {
            bindSimpleForecast(it)
        }

        viewModel.oneCallForecastForecast.observe(viewLifecycleOwner) {
            bindOneCallForecast(it)
            hourlyRecyclerViewAdapter.hourlyItems = it.hourly!!
            dailyRecyclerViewAdapter.dailyItems = it.daily!!
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

    private fun bindOneCallForecast(oneCallForecast: OneCallForecast) {
        val simpleForecast = oneCallForecast.simpleForecast
        binding.cityName.text = simpleForecast?.geography?.city.toString()
        binding.currentTemperature.text = simpleForecast?.temperature?.temp?.roundToInt().toString() + "°"
        binding.minTemperature.text = simpleForecast?.temperature?.tempMin?.roundToInt().toString() + "°"
        binding.maxTemperature.text = simpleForecast?.temperature?.tempMax?.roundToInt().toString() + "°"
        binding.feelsTemperature.text = simpleForecast?.temperature?.tempFeelsLike?.roundToInt().toString() + "°"
        binding.weatherDescription.text = simpleForecast?.description?.title
        binding.sunImageView.setImageResource(
            WeatherCodeConverter.getResourceCode(simpleForecast?.description?.icon.toString())
        )
    }

}