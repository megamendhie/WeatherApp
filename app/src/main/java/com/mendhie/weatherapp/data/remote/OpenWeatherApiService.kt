package com.mendhie.weatherapp.data.remote

import com.mendhie.weatherapp.BuildConfig
import com.mendhie.weatherapp.data.models.WeatherForecast
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.openweathermap.org/"
const val LONGITUDE = -94.04
const val LATITUDE = 33.44
const val END_POINT = "data/2.5/onecall?"
const val METRIC = "metric"
const val WEEKLY_FORECAST = "${END_POINT}lat=$LATITUDE&lon=$LONGITUDE&units=$METRIC&exclude=hourly,minutely"
const val APP_ID = BuildConfig.WEATHER_API_KEY
const val weeklyUrl = "$WEEKLY_FORECAST&appid=$APP_ID"

val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

interface OpenWeatherApiService{

    @GET(weeklyUrl)
    fun getWeeklyForecast(): Call<WeatherForecast>
}

object OpenWeatherApi{
    val WeatherApi: OpenWeatherApiService by lazy {
        retrofit.create(OpenWeatherApiService::class.java)
    }
}