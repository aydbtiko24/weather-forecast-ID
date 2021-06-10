package com.highair.weatherforecastid.data.repository

import com.highair.weatherforecastid.models.Weather
import kotlinx.coroutines.flow.Flow
import com.highair.weatherforecastid.data.Result

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
interface WeatherRepository {
    fun getCurrentWeather(
        update: Boolean,
        regionId: Long,
        currentDateTime: Long = System.currentTimeMillis()
    ): Flow<Result<Weather>>

    fun getWeatherDates(): Flow<List<String>>
    fun getWeathersByDate(selectedDate: Long): Flow<List<Weather>>
}
