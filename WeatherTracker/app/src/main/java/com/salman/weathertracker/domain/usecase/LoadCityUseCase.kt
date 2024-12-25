package com.salman.weathertracker.domain.usecase

import com.salman.weathertracker.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(): Flow<String?> {
        return repository.getSavedCity()
    }
}