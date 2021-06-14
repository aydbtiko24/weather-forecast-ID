package com.highair.weatherforecastid.data.source

import com.highair.weatherforecastid.models.Weather
import kotlinx.coroutines.flow.Flow

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
interface WeatherLocalDataSource {
    suspend fun addWeathers(weathers: List<Weather>)
    fun getCurrentWeather(currentDateTime: Long, currentRegionId: Long): Flow<Weather>
    fun getWeatherDates(): Flow<List<String>>
    fun getWeathersByDate(selectedDate: Long): Flow<List<Weather>>
}
