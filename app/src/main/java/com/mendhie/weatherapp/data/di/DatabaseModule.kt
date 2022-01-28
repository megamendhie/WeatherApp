package com.mendhie.weatherapp.data.di

import android.app.Application
import androidx.room.Room
import com.mendhie.weatherapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(app: Application, callback: AppDatabase.WeatherCallback): AppDatabase
        =  Room.databaseBuilder(app, AppDatabase::class.java, "weatherapp_db")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Singleton
    @Provides
    fun providesWeatherDoa(db: AppDatabase) = db.WeatherDao()

    @Singleton
    @Provides
    fun provideAppScope() = CoroutineScope(SupervisorJob())
}