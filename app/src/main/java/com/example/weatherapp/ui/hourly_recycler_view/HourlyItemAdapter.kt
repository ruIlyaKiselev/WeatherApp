package com.example.weatherapp.ui.hourly_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.HourlyItemBinding
import com.example.weatherapp.domain.HourlyItem
import com.example.weatherapp.network.open_weather_map.WeatherCodeConverter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HourlyItemAdapter: RecyclerView.Adapter<HourlyItemAdapter.HourlyItemViewHolder>() {

    var hourlyItems: List<HourlyItem> = emptyList()
    set(value) {
        field = value.take(24)
        notifyDataSetChanged()
    }

    class HourlyItemViewHolder(
        val binding: HourlyItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HourlyItemBinding.inflate(inflater, parent, false)
        return HourlyItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyItemViewHolder, position: Int) {
        val hourlyItem = hourlyItems[position]
        val dataFormatter = SimpleDateFormat("HH:mm")
        val date = Date(1000 * (hourlyItem.time?.toLong()?: 0))

        with(holder.binding) {
            this.hourlyItemTimeTextView.text = dataFormatter.format(date)
            this.hourlyItemSunImageView.setImageResource(
                WeatherCodeConverter.getResourceCode(hourlyItem.icon.toString())
            )
            this.hourlyItemTemperature.text = hourlyItem.temp?.roundToInt().toString() + "°"
        }
    }

    override fun getItemCount(): Int {
        return hourlyItems.size
    }
}