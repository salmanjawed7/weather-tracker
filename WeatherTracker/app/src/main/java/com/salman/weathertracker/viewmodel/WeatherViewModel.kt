package com.salman.weathertracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salman.weathertracker.domain.model.Weather
import com.salman.weathertracker.domain.usecase.GetWeatherUseCase
import com.salman.weathertracker.domain.usecase.LoadCityUseCase
import com.salman.weathertracker.domain.usecase.RemoveCityUseCase
import com.salman.weathertracker.domain.usecase.SaveCityUseCase
import com.salman.weathertracker.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val loadCityUseCase: LoadCityUseCase,
    private val removeCityUseCase: RemoveCityUseCase,
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    init {
        loadSavedCity()
    }

    private fun loadSavedCity() {
        loadCityUseCase().onEach { city ->
            city?.let { fetchWeather(it) } ?: run { _weatherState.value = WeatherState.NoCitySelected }
        }.launchIn(viewModelScope)
    }

    private fun fetchWeather(city: String) {
        viewModelScope.launch {
            val result = getWeatherUseCase(city)
            _weatherState.value = result.fold(
                onSuccess = { WeatherState.Success(it) },
                onFailure = { WeatherState.FetchWeatherFailed }
            )
        }
    }

    fun searchCity(cityName: String) {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch {
            val result = searchCityUseCase(cityName)
            _weatherState.value = result.fold(
                onSuccess = { WeatherState.SearchSuccess(it) },
                onFailure = {
                    WeatherState.SearchFailed
                }
            )
        }
    }

    fun saveCity(cityName: String) {
        viewModelScope.launch {
            saveCityUseCase.invoke(cityName)
        }
    }
}

sealed class WeatherState {
    data object Loading : WeatherState()  // Indicates data is being loaded
    data class Success(val weather: Weather) : WeatherState()  // Successfully fetched weather data
    data class SearchSuccess(val weathers: List<Weather>) : WeatherState()  // Successfully fetched weather data
    data object NoCitySelected: WeatherState()
    data object SearchFailed: WeatherState()
    data object FetchWeatherFailed: WeatherState()
}