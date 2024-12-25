package com.salman.weathertracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import com.salman.weathertracker.ui.WeatherAppUi
import com.salman.weathertracker.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        setContent {
            WeatherAppUi(
                weatherState = weatherViewModel.weatherState.collectAsState().value,
                onSearchCity = weatherViewModel::searchCity,
                onSaveCity = weatherViewModel::saveCity
            )
        }
    }
}

