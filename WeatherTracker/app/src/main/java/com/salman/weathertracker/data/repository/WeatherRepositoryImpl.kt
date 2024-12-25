package com.salman.weathertracker.data.repository

import com.salman.weathertracker.data.api.WeatherApi
import com.salman.weathertracker.data.local.LocalStorage
import com.salman.weathertracker.domain.model.Weather
import com.salman.weathertracker.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val apiService: WeatherApi,
    private val localStorage: LocalStorage
) : WeatherRepository {

    // Fetch weather data for the city
    override suspend fun getWeatherForCity(city: String): Weather {
        return try {
            val response = apiService.getWeather(city = city)
            if (response.isSuccessful && response.body() != null) {
                val weatherResponse = response.body()!!
                Weather(
                    cityName = weatherResponse.location.cityName,
                    temperature = weatherResponse.current.temperatureCelsius.toInt(),
                    condition = weatherResponse.current.condition.description,
                    humidity = weatherResponse.current.humidity,
                    uvIndex = weatherResponse.current.uvIndex.toInt(),
                    feelsLike = weatherResponse.current.feelsLikeCelsius.toInt(),
                    iconUrl = weatherResponse.current.condition.iconUrl
                )
            } else {
                throw Exception("Error fetching weather")
            }
        } catch (e: Exception) {
            throw e // Propagate the exception
        }
    }

    // Save the selected city using LocalStorage
    override suspend fun saveCity(cityName: String) {
        localStorage.saveCity(cityName)
    }

    // Retrieve the saved city using LocalStorage
    override fun getSavedCity(): Flow<String?> {
        return localStorage.selectedCity
    }

    // Remove the save city from local storage
    override suspend fun removeCity() {
        localStorage.removeCity()
    }

    override suspend fun getCities(query: String): List<String> {
        return try {
            val response = apiService.search(city = query)
            if (response.isSuccessful && response.body() != null) {
                val searchResponse = response.body()!!
                searchResponse.map { it.cityName }
            } else {
                throw Exception("Error fetching cities")
            }
        } catch (e: Exception) {
            throw e // Propagate the exception
        }
    }
}