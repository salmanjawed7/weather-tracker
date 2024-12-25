package com.salman.weathertracker.domain.usecase

import com.salman.weathertracker.domain.repository.WeatherRepository
import javax.inject.Inject

class RemoveCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke() {
        repository.removeCity()
    }
}