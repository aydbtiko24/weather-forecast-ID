package com.highair.weatherforecastid.weather

import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.models.Weather

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
data class WeatherViewState(
    val dataLoading: Boolean,
    val selectedRegion: Region,
    val currentWeather: Weather,
    val weatherDates: List<WeatherDateOption>,
    val weathers: List<Weather>,
) {
    companion object {
        val EMPTY = WeatherViewState(
            dataLoading = false,
            selectedRegion = Invalid.region.copy(id = 0),
            currentWeather = Invalid.weather,
            weatherDates = emptyList(),
            weathers = emptyList()
        )
    }
}
