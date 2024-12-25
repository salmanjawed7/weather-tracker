package com.salman.weathertracker.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: CurrentWeather
)

data class Location(
    @SerializedName("name") val cityName: String,
    @SerializedName("region") val region: String?,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("tz_id") val timezone: String,
    @SerializedName("localtime") val localTime: String
)

data class CurrentWeather(
    @SerializedName("temp_c") val temperatureCelsius: Double,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("uv") val uvIndex: Double
)

data class Condition(
    @SerializedName("text") val description: String,
    @SerializedName("icon") val iconUrl: String,
    @SerializedName("code") val code: Int
)