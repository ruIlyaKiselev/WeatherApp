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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
            binding.maicScreenTextView.text = it.toString()
        }

        return view
    }

}