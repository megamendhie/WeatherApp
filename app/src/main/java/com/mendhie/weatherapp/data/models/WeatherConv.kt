package com.mendhie.weatherapp.data.models

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import java.io.IOException

class WeatherConv {
    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun weatherToJson(weather: WeatherForecast): String? {
        return if (weather == null) null else moshi.adapter<Any>(WeatherForecast::class.java).toJson(weather)
    }

    @TypeConverter
    fun weatherFromJson(string: String): WeatherForecast? {
        val weather: WeatherForecast? = try {
            moshi.adapter(WeatherForecast::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return weather
    }

    @TypeConverter
    fun currentToJson(currentWeather: CurrentWeather): String? {
        return if (currentWeather == null) null else moshi.adapter<Any>(CurrentWeather::class.java).toJson(currentWeather)
    }

    @TypeConverter
    fun currentFromJson(string: String): CurrentWeather? {
        val currentWeather: CurrentWeather? = try {
            moshi.adapter(CurrentWeather::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return currentWeather
    }

    @TypeConverter
    fun tempToJson(temp: Temp): String? {
        return if (temp == null) null else moshi.adapter<Any>(Temp::class.java).toJson(temp)
    }

    @TypeConverter
    fun tempFromJson(string: String): Temp? {
        val temp: Temp? = try {
            moshi.adapter(Temp::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return temp
    }
}