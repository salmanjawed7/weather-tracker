package com.salman.weathertracker.di

import android.content.Context
import com.salman.weathertracker.data.local.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    // Provide the ApplicationContext using the @ApplicationContext annotation
    @Provides
    @Singleton
    fun provideLocalStorage(@ApplicationContext context: Context): LocalStorage {
        return LocalStorage(context)
    }
}