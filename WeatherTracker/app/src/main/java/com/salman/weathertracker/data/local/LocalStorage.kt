package com.salman.weathertracker.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "weather_app_preferences")

class LocalStorage(private val context: Context) {

    companion object {
        val CITY_KEY = stringPreferencesKey("selected_city")
    }

    suspend fun saveCity(cityName: String) {
        context.dataStore.edit { preferences ->
            preferences[CITY_KEY] = cityName
        }
    }

    suspend fun removeCity() {
        context.dataStore.edit { preferences ->
            preferences.remove(CITY_KEY)
        }
    }

    val selectedCity: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[CITY_KEY] }
}