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

    /**
     * @return true if
     * [selectedRegion] id is invalid id
     */
    val hasInvalidRegion: Boolean
        get() = selectedRegion.id == Invalid.id

    /**
     * @return true if
     * [dataLoading] is false
     * [selectedRegion] id is not initial id
     * [selectedRegion] id is not invalid id
     * and [currentWeather] id is not invalid id
     */
    val hasContent: Boolean
        get() = !dataLoading &&
                selectedRegion.id != 0L &&
                selectedRegion.id != Invalid.id &&
                currentWeather.id != Invalid.id

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
