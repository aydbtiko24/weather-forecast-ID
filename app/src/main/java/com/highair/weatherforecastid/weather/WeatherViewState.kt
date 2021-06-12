package com.highair.weatherforecastid.weather

import androidx.compose.runtime.Immutable
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.models.Weather

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@Immutable
data class WeatherViewState(
    val dataLoading: Boolean,
    val selectedRegion: Region,
    val currentWeather: Weather,
    val weatherDates: List<WeatherDateOption>,
    val weathers: List<Weather>,
)
