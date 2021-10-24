package com.example.weatherapp.ui.stored_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentStoredBinding
import com.example.weatherapp.domain.OneCallForecast
import com.example.weatherapp.ui.view_pager.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoredFragment : Fragment() {

    private val viewModel: StoredViewModel by viewModels()
    private lateinit var binding: FragmentStoredBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val data = mutableListOf<OneCallForecast>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_stored,
            container,
            false
        )

        binding.lifecycleOwner = this

        val view: View = binding.root

        viewModel.loadOneCallForecastByGeographicCoordinates(55.751244, 37.618423)
        viewModel.loadOneCallForecastByGeographicCoordinates(55.018803, 82.933952)
        viewModel.loadOneCallForecastByGeographicCoordinates(59.937500, 30.308611)

        viewModel.oneCallForecastList.observe(viewLifecycleOwner) {
            data.add(it)
            Log.d("MyLog1111", "aaaaaaaaaaaaaaaaaaaaaaaaaaa")
            viewPagerAdapter = ViewPagerAdapter(this, data)
            binding.fragmentStoredViewPager.adapter = viewPagerAdapter
        }

//        viewPagerAdapter = ViewPagerAdapter(this, data)
//        binding.fragmentStoredViewPager.adapter = viewPagerAdapter


        return view
    }
}