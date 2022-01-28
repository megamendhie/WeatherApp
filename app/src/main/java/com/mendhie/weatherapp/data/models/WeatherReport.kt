package com.mendhie.weatherapp.data.models

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_table")
@TypeConverters(WeatherConverter::class)
data class WeatherForecast(
    @PrimaryKey
    val id: Int = 0,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset") val offset: Int,
    @Embedded val current: CurrentWeather,
    val daily: List<DailyForecast>
)

data class DailyForecast(
    @SerializedName("dt") val date: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    @SerializedName("moon_phase") val moonPhase: Double,
    val temp: Temp,
    @SerializedName("feels_like") val feelsLike: Feeling,
    val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    val weather: List<Weather>,
    val uvi: Double
)

data class Temp(
    val day: Double? = 0.0,
    val min: Double? = 0.0,
    val max: Double? = 0.0,
    val night: Double? = 0.0,
    @SerializedName("eve") val evening: Double? = 0.0,
    @SerializedName("morn") val morning: Double? = 0.0
)

data class Feeling(
    val day: Double,
    val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morning: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CurrentWeather(
    @SerializedName("dt") val date: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    @SerializedName("moon_phase") val moonPhase: Double,
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    val weather: List<Weather>,
    val uvi: Double
)