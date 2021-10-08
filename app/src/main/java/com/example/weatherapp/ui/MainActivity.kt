package com.example.weatherapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.BaseApplication
import com.example.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    @Inject lateinit var baseApplication: BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.searchFragment,
                R.id.mainScreenFragment,
                R.id.storedFragment
            )
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
            when (destination.id) {
                R.id.mainScreenFragment -> {
                    bottomNav.visibility = View.VISIBLE
                    window.statusBarColor = ContextCompat.getColor(this, R.color.background)
                }
                R.id.searchFragment ->  {
                    bottomNav.visibility = View.VISIBLE
                    window.statusBarColor = ContextCompat.getColor(this, R.color.background)
                }
                R.id.storedFragment -> {
                    bottomNav.visibility = View.VISIBLE
                    window.statusBarColor = ContextCompat.getColor(this, R.color.background)
                }
                R.id.mapsFragment -> {
                    bottomNav.visibility = View.GONE
                    window.statusBarColor = Color.TRANSPARENT
                }
            }
        }

        getGeolocation()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()
    }

    private fun fetchLocationPermissions() {
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        val coarseLocation = ActivityCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (fineLocationPermission != PackageManager.PERMISSION_GRANTED
            && coarseLocation != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
        }
    }

    private fun getGeolocation() {
        fetchLocationPermissions()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            val task = fusedLocationProviderClient?.lastLocation
            task?.addOnSuccessListener {
                if (task.isSuccessful() && task.getResult() != null) {
                    baseApplication.setLocation(it)
                }
            }
        }
    }
}