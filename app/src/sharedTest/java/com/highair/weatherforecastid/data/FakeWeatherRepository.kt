package com.highair.weatherforecastid.data

import com.highair.weatherforecastid.data.repository.WeatherRepository
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.utils.asDateString
import com.highair.weatherforecastid.utils.toEndDateTime
import com.highair.weatherforecastid.utils.toStartDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
class FakeWeatherRepository : WeatherRepository {

    private val weathers = HashMap<Long, Weather>().apply {
        weatherEntities.asDomainModels().forEach { weather ->
            this[weather.id] = weather
        }
    }

    var shouldLoading = false

    override fun getCurrentWeather(
        update: Boolean,
        regionId: Long,
        currentDateTime: Long
    ): Flow<Result<Weather>> = flow {
        if (shouldLoading || update) {
            emit(Result.Loading)
        } else {
            emit(Result.Success(weathers[2L]!!))
        }
    }

    override fun getWeatherDates(): Flow<List<String>> {
        return flowOf(weathers.toList().map {
            it.second.dateTime.asDateString()
        }.distinct())
    }

    override fun getWeathersByDate(selectedDate: Long): Flow<List<Weather>> {
        val startTime = selectedDate.toStartDateTime()
        val endTime = selectedDate.toEndDateTime()
        val weathers = weathers.values.toList().filter { weather ->
            weather.dateTime in startTime..endTime
        }
        return flowOf(weathers)
    }
}