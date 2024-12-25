package com.salman.weathertracker.domain.usecase

import com.salman.weathertracker.domain.model.Weather
import com.salman.weathertracker.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: String): Result<Weather> {
        return try {
            val weather = repository.getWeatherForCity(city)
            Result.success(weather)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}