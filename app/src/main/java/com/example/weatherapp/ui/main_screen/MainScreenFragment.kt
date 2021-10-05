package com.example.weatherapp.ui.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.button.setOnClickListener {
            viewModel.loadForecastByCityName(binding.cityNameEditText.text.toString())
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        viewModel.trackedForecast.observe(viewLifecycleOwner) {
            binding.weatherResponce.text = it.toString()
        }

        viewModel.loadForecastByCityName("Novosibirsk")
        viewModel.loadForecastByGeographicCoordinates(35, 139)
    }
}