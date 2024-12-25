package com.salman.weathertracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.salman.weathertracker.viewmodel.WeatherState

@Composable
fun WeatherAppUi(
    weatherState: WeatherState,
    onSearchCity: (String) -> Unit,
    onSaveCity: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchField(modifier = Modifier.align(Alignment.CenterHorizontally), onValueChange = onSearchCity)
        when (weatherState) {
            is WeatherState.Success -> {
                val weather = weatherState.weather
                SuccessResultState(weather)  // Show weather data
            }

            is WeatherState.NoCitySelected -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "No City Selected",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "Please Search For A City",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            WeatherState.FetchWeatherFailed -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "Failed to fetch weather",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
            WeatherState.SearchFailed -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "Failed to search",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
            is WeatherState.SearchSuccess -> {
                val weathers = weatherState.weathers
                SearchResultState(weathers, onSaveCity)  // Show weather data
            }

            WeatherState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(Modifier.size(80.dp))
                }
            }
        }
    }
}