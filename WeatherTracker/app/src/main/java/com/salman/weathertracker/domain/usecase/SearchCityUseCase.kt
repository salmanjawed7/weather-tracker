package com.salman.weathertracker.domain.usecase

import com.salman.weathertracker.domain.model.Weather
import com.salman.weathertracker.domain.repository.WeatherRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(query: String): Result<List<Weather>> {
        return try {
            val listOfCities = repository.getCities(query)
            val weathersList = mutableListOf<Weather>()
            listOfCities.forEach {
                weathersList.add(repository.getWeatherForCity(it))
            }
            Result.success(weathersList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}