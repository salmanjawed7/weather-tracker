package com.salman.weathertracker.data.api

import com.salman.weathertracker.BuildConfig
import com.salman.weathertracker.data.model.SearchLocation
import com.salman.weathertracker.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") city: String
    ): Response<WeatherResponse>

    @GET("search.json")
    suspend fun search(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") city: String
    ): Response<List<SearchLocation>>
}