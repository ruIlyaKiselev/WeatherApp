package com.example.weatherapp.network.open_weather_map

import com.example.weatherapp.R

class WeatherCodeConverter {
    companion object {
        fun getResourceCode(imageCode: String): Int {
            return when (imageCode) {
                "01d" -> R.drawable.d
                "01n" -> R.drawable.n
                "02d" -> R.drawable.d_c1
                "02n" -> R.drawable.n_c1
                "03d" -> R.drawable.d_c2
                "03n" -> R.drawable.n_c2
                "04d" -> R.drawable.c3
                "04n" -> R.drawable.c3
                "09d" -> R.drawable.c3_r3
                "09n" -> R.drawable.c3_r3
                "10d" -> R.drawable.d_c2_r3
                "10n" -> R.drawable.n_c2_r3
                "11d" -> R.drawable.c3_st
                "11n" -> R.drawable.c3_st
                "13d" -> R.drawable.c3_s3
                "13n" -> R.drawable.c3_s3
                "50d" -> R.drawable.mist
                "50n" -> R.drawable.mist
                else -> R.drawable.d
            }
        }
    }
}