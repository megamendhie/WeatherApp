package com.mendhie.weatherapp.domain.mappers

import com.mendhie.weatherapp.data.models.CurrentWeather
import com.mendhie.weatherapp.data.models.DisplayWeather
import java.text.SimpleDateFormat
import java.util.*

class WeatherDataMapper {

    fun convertToDisplayWeather(timeZone: String, currentWeather: CurrentWeather): DisplayWeather{
        val f = DisplayWeather(timeZone.split("/").get(1),
            dateFormatter(currentWeather.date),
            currentWeather.weather[0].description,
            currentWeather.weather[0].icon,
            currentWeather.temp,
            currentWeather.feelsLike,
            currentWeather.humidity,
            (currentWeather.windSpeed*3.6),
            currentWeather.uvi
        )
        return f
    }

    fun dateFormatter(dt: Long): String{
        val sdf = SimpleDateFormat("EEEE MMMM dd")
        val date = Date(dt * 1000)
        return sdf.format(date)
    }

    fun getDay(dt: Long): String{
        val sdf = SimpleDateFormat("E")
        val date = Date(dt * 1000)
        return sdf.format(date)
    }
}