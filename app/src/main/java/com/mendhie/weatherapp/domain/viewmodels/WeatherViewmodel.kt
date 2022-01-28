package com.mendhie.weatherapp.domain.viewmodels

import androidx.lifecycle.*
import com.mendhie.weatherapp.data.models.WeatherForecast
import com.mendhie.weatherapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewmodel @Inject constructor(repository: Repository): ViewModel (){
    var weatherForecast: LiveData<WeatherForecast> = repository.getWeatherReport()

}