package com.example.weatherapp.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var binding: FragmentMainScreenBinding

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

        val view: View = binding.root

        binding.button.setOnClickListener {
            viewModel.loadForecastByCityName(binding.cityNameEditText.text.toString())
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        viewModel.trackedForecast.observe(viewLifecycleOwner) {
            binding.weatherResponseText = it.toString()
        }

        viewModel.loadForecastByCityName("Novosibirsk")
        viewModel.loadForecastByGeographicCoordinates(35, 139)
    }
}