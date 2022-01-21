package com.mendhie.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mendhie.weatherapp.data.models.DailyForecast
import com.mendhie.weatherapp.databinding.ItemWeatherBinding
import com.mendhie.weatherapp.domain.mappers.WeatherDataMapper

class WeatherAdapter(): RecyclerView.Adapter<WeatherAdapter.WeatherItemViewHolder>() {
    var list: MutableList<DailyForecast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(updatedList: MutableList<DailyForecast>){
        list = updatedList
        notifyDataSetChanged()
    }

    inner class WeatherItemViewHolder(val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(dailyForecast: DailyForecast){
            binding.txtDesc.text = dailyForecast.weather[0].description
            binding.txtDate.text = WeatherDataMapper().getDay(dailyForecast.date)
            binding.txtTemp.text = "${dailyForecast.temp.max}/${dailyForecast.temp.min}"
        }
    }
}