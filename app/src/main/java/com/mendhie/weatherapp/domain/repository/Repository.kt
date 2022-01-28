package com.mendhie.weatherapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.mendhie.weatherapp.data.database.WeatherDao
import com.mendhie.weatherapp.data.models.WeatherForecast
import com.mendhie.weatherapp.data.remote.OpenWeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(val dao: WeatherDao, val scope: CoroutineScope){
    val TAG = "Repository"

    fun getWeatherReport(): LiveData<WeatherForecast>{
        scope.launch { getFromApi() }
        return getFromDatabase()
    }

    private fun getFromDatabase(): LiveData<WeatherForecast> = dao.getWeatherForecast()

    suspend fun saveToDatabase(weatherForecast: WeatherForecast){
        dao.insert(weatherForecast)
    }

    private suspend fun getFromApi(){
        OpenWeatherApi.WeatherApi.getWeeklyForecast().enqueue(object : Callback<WeatherForecast>{
            override fun onResponse(
                call: Call<WeatherForecast>,
                response: Response<WeatherForecast>
            ) {
                scope.launch { saveToDatabase(response.body()!!) }
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
    }

}