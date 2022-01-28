package com.mendhie.weatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mendhie.weatherapp.data.models.WeatherConverter
import com.mendhie.weatherapp.data.models.WeatherForecast

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table")
    fun getWeatherForecast(): LiveData<WeatherForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherForecast: WeatherForecast)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}