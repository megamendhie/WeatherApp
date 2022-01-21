package com.mendhie.weatherapp.domain.viewmodels

import androidx.lifecycle.*
import com.mendhie.weatherapp.data.models.WeatherForecast
import com.mendhie.weatherapp.domain.repository.Repository

class WeatherViewmodel: ViewModel(){
    var weatherForecast: MutableLiveData<WeatherForecast> = Repository().getWeatherRepor()

}