package com.salman.weathertracker.di

import com.salman.weathertracker.data.api.WeatherApi
import com.salman.weathertracker.data.local.LocalStorage
import com.salman.weathertracker.data.repository.WeatherRepositoryImpl
import com.salman.weathertracker.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        localStorage: LocalStorage
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi, localStorage)
    }
}