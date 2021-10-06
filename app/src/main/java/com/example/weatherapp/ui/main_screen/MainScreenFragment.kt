package com.example.weatherapp.ui.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import com.jakewharton.rxbinding4.widget.textChangeEvents
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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

        binding.lifecycleOwner = this

        val view: View = binding.root

        binding.viewModel = viewModel


        binding.cityNameEditText.textChangeEvents()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.loadForecastByCityName(it.text.toString())
            }, {
                Log.e("MyLog","CoroutineExceptionHandler got ${it.localizedMessage}")
            })

        return view
    }
}