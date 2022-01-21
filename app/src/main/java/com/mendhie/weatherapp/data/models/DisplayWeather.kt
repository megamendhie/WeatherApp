package com.mendhie.weatherapp.data.models

data class DisplayWeather(
    val city: String,
    val date: String,
    val description: String,
    val icon: String,
    val temp: Double,
    val feelsLike: Double,
    val humidity: Int,
    val wind: Double,
    val uvi: Double
)
