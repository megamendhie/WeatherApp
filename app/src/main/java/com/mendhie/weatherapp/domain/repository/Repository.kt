package com.mendhie.weatherapp.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mendhie.weatherapp.data.models.WeatherForecast
import com.mendhie.weatherapp.data.remote.OpenWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    val TAG = "Repository"

    fun getWeatherRepor(): MutableLiveData<WeatherForecast>{
        val data = MutableLiveData<WeatherForecast>()
        OpenWeatherApi.WeatherApi.getWeeklyForecast().enqueue(object : Callback<WeatherForecast>{
            override fun onResponse(
                call: Call<WeatherForecast>,
                response: Response<WeatherForecast>
            ) {
                data.value = response.body()
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
        return data
    }

}