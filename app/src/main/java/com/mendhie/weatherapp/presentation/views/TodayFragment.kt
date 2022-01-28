package com.mendhie.weatherapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mendhie.weatherapp.data.models.DisplayWeather
import com.mendhie.weatherapp.databinding.FragmentTodayBinding
import com.mendhie.weatherapp.domain.mappers.WeatherDataMapper
import com.mendhie.weatherapp.domain.viewmodels.WeatherViewmodel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val viewModel: WeatherViewmodel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.weatherForecast.observe(viewLifecycleOwner, { weatherForecast ->
            if(weatherForecast==null)
                return@observe
            val displayWeather = WeatherDataMapper().convertToDisplayWeather(
                weatherForecast.timezone,
                weatherForecast.current
            )

            updateViews(displayWeather)
        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateViews(displayWeather: DisplayWeather) {
        binding.txtCity.text = displayWeather.city
        binding.txtDate.text = displayWeather.date
        binding.txtDesc.text = displayWeather.description
        binding.txtTemp.text = "${displayWeather.temp}"
        binding.txtDegree.visibility = View.VISIBLE
        binding.txtFeelsLike.text = "${displayWeather.feelsLike}"
        binding.txtCelcius.visibility = View.VISIBLE
        binding.txtWind.text = String.format("%.2fkm/h", displayWeather.wind)
        binding.txtHumidity.text = "${displayWeather.humidity}%"
        binding.txtUV.text = "${displayWeather.uvi}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}