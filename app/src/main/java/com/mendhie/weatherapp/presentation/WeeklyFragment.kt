package com.mendhie.weatherapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mendhie.weatherapp.data.models.DailyForecast
import com.mendhie.weatherapp.data.models.DisplayWeather
import com.mendhie.weatherapp.databinding.FragmentWeeklyBinding
import com.mendhie.weatherapp.domain.mappers.WeatherDataMapper
import com.mendhie.weatherapp.domain.viewmodels.WeatherViewmodel
import com.mendhie.weatherapp.presentation.adapters.WeatherAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WeeklyFragment : Fragment() {

    private var _binding: FragmentWeeklyBinding? = null
    private lateinit var viewModel: WeatherViewmodel
    private lateinit var adapter: WeatherAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeeklyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lstDailyForecast.layoutManager = LinearLayoutManager(requireContext())
        adapter = WeatherAdapter()
        binding.lstDailyForecast.adapter = adapter
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewmodel::class.java)
        viewModel.weatherForecast.observe(viewLifecycleOwner, { weatherForecast ->
            val displayWeather = WeatherDataMapper().convertToDisplayWeather(
                weatherForecast.timezone,
                weatherForecast.current
            )

            updateViews(displayWeather)
            val list = weatherForecast.daily?.toMutableList()
            list?.removeAt(0)
            updateList(list!!)
        })
    }

    private fun updateList(daily: MutableList<DailyForecast>) {
        adapter.updateList(daily)
    }

    private fun updateViews(displayWeather: DisplayWeather) {
        binding.txtCity.text = displayWeather.city
        binding.txtDate.text = displayWeather.date
        binding.txtDesc.text = displayWeather.description
        binding.txtTemp.text = "${displayWeather.temp}"
        binding.txtDegree.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}