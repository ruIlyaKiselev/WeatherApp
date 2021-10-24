package com.example.weatherapp.ui.view_pager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.domain.OneCallForecast

class ViewPagerAdapter(
    fragment: Fragment,
    val data: MutableList<OneCallForecast>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {

        Log.d("MyLog1111", "aaaaaaaaaaaaaaaaaaaaaaaaaaa")
        val fragment = ViewPagerFragment()
        fragment.bindOneCallForecast(data[position])

        return fragment
    }
}