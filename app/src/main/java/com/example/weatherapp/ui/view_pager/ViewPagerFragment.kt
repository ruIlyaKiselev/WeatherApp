package com.example.weatherapp.ui.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ViewPagerItemBinding
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.domain.SimpleForecast
import com.example.weatherapp.network.open_weather_map.WeatherCodeConverter
import com.example.weatherapp.ui.daily_recycler_view.DailyItemAdapter
import com.example.weatherapp.ui.hourly_recycler_view.HourlyItemAdapter
import kotlin.math.roundToInt

class ViewPagerFragment: Fragment() {

    private lateinit var binding: ViewPagerItemBinding

    private lateinit var hourlyRecyclerViewAdapter: HourlyItemAdapter
    private lateinit var dailyRecyclerViewAdapter: DailyItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.view_pager_item,
            container,
            false
        )

        binding.lifecycleOwner = this

        hourlyRecyclerViewAdapter = HourlyItemAdapter()

        val hourlyLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.viewPagerHourlyRecyclerView.layoutManager = hourlyLayoutManager
        binding.viewPagerHourlyRecyclerView.adapter = hourlyRecyclerViewAdapter

        dailyRecyclerViewAdapter = DailyItemAdapter()

        val dailyLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.viewPagerDailyRecyclerView.layoutManager = dailyLayoutManager
        binding.viewPagerDailyRecyclerView.adapter = dailyRecyclerViewAdapter

        val view: View = binding.root

        return view
    }

    fun bindSimpleForecast(simpleForecast: SimpleForecast) {
        binding.viewPagerCityName.text = simpleForecast.geography?.city.toString()
        binding.viewPagerCurrentTemperature.text = simpleForecast.temperature?.temp?.roundToInt().toString() + "°"
        binding.viewPagerMinTemperature.text = simpleForecast.temperature?.tempMin?.roundToInt().toString() + "°"
        binding.viewPagerMaxTemperature.text = simpleForecast.temperature?.tempMax?.roundToInt().toString() + "°"
        binding.viewPagerFeelsTemperature.text = simpleForecast.temperature?.tempFeelsLike?.roundToInt().toString() + "°"
        binding.viewPagerWeatherDescription.text = simpleForecast.description?.title
        binding.viewPagerSunImageView.setImageResource(
            WeatherCodeConverter.getResourceCode(simpleForecast.description?.icon.toString())
        )
        binding.viewPagerConstraintLayout.visibility = View.VISIBLE
    }

    fun bindOneCallForecast(oneCallForecast: OneCallForecast) {
        val simpleForecast = oneCallForecast.simpleForecast
        try {
            binding.viewPagerCityName.text = simpleForecast?.geography?.city.toString()
            binding.viewPagerCurrentTemperature.text =
                simpleForecast?.temperature?.temp?.roundToInt().toString() + "°"
            binding.viewPagerMinTemperature.text =
                simpleForecast?.temperature?.tempMin?.roundToInt().toString() + "°"
            binding.viewPagerMaxTemperature.text =
                simpleForecast?.temperature?.tempMax?.roundToInt().toString() + "°"
            binding.viewPagerFeelsTemperature.text =
                simpleForecast?.temperature?.tempFeelsLike?.roundToInt().toString() + "°"
            binding.viewPagerWeatherDescription.text = simpleForecast?.description?.title
            binding.viewPagerSunImageView.setImageResource(
                WeatherCodeConverter.getResourceCode(simpleForecast?.description?.icon.toString())
            )
            binding.viewPagerConstraintLayout.visibility = View.VISIBLE
        } catch (e: Exception) {}
    }
}