package com.salman.weathertracker.domain.model

data class Weather(
    val cityName: String,
    val temperature: Int,
    val condition: String,
    val iconUrl: String,
    val feelsLike: Int,
    val humidity: Int,
    val uvIndex: Int
)