package com.salman.weathertracker.domain.usecase

import com.salman.weathertracker.domain.repository.WeatherRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String) {
        repository.saveCity(cityName)
    }
}