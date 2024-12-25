package com.salman.weathertracker.domain.repository

import com.salman.weathertracker.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForCity(city: String): Weather
    suspend fun saveCity(cityName: String)
    suspend fun removeCity()
    fun getSavedCity(): Flow<String?>
    suspend fun getCities(query: String): List<String>
}