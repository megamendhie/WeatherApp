package com.mendhie.weatherapp.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mendhie.weatherapp.data.models.DailyForecast
import com.mendhie.weatherapp.data.models.Weather

class WeatherConverter{
    companion object{
        @JvmStatic
        @TypeConverter
        fun dailyForecastFromJson(json: String?): List<DailyForecast>
                = if(json==null) listOf() else Gson().fromJson(json, object : TypeToken<List<DailyForecast>>(){}.type)

        @JvmStatic
        @TypeConverter
        fun dailyForecastToJson(data: List<DailyForecast>)
        = Gson().toJson(data)

        @JvmStatic
        @TypeConverter
        fun weatherFromJson(json: String?): List<Weather>
                = if(json==null) listOf() else Gson().fromJson(json, object : TypeToken<List<Weather>>(){}.type)

        @JvmStatic
        @TypeConverter
        fun weatherToJson(data: List<Weather>)
                = Gson().toJson(data)
    }
}