package com.example.weatherapp.ui.daily_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.DailyItemBinding
import com.example.weatherapp.domain.DailyItem
import com.example.weatherapp.network.open_weather_map.WeatherCodeConverter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class DailyItemAdapter: RecyclerView.Adapter<DailyItemAdapter.DailyItemViewHolder>() {

    var dailyItems: List<DailyItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class DailyItemViewHolder(
        val binding: DailyItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DailyItemBinding.inflate(inflater, parent, false)
        return DailyItemAdapter.DailyItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyItemViewHolder, position: Int) {
        val dailyItem = dailyItems[position]
        val dayOfWeekFormatter = SimpleDateFormat("E")
        val dataFormatter = SimpleDateFormat("MMM:d")
        val date = Date(1000 * (dailyItem.date?.toLong()?: 0))


        with(holder.binding) {

            this.dailyItemDayOfWeek.text = dayOfWeekFormatter.format(date)
            this.dailyItemDate.text = dataFormatter.format(date)
            this.dailyItemSunImageView.setImageResource(
                WeatherCodeConverter.getResourceCode(dailyItem.icon.toString())
            )
            this.dailyItemMaxValue.text = dailyItem.tempMax?.roundToInt().toString() + "°"
            this.dailyItemMinValue.text = dailyItem.tempMin?.roundToInt().toString() + "°"
        }
    }

    override fun getItemCount(): Int {
        return dailyItems.size
    }
}