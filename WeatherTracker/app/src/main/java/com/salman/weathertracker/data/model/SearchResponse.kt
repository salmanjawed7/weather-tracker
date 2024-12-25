package com.salman.weathertracker.data.model

import com.google.gson.annotations.SerializedName

data class SearchLocation(
    @SerializedName("name") val cityName: String,
    @SerializedName("region") val region: String?,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
)