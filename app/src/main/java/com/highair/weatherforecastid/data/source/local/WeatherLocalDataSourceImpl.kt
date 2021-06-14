package com.highair.weatherforecastid.data.source.local

import com.highair.weatherforecastid.data.source.WeatherLocalDataSource
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.utils.asDateString
import com.highair.weatherforecastid.utils.findClosestWeatherTime
import com.highair.weatherforecastid.utils.toEndDateTime
import com.highair.weatherforecastid.utils.toStartDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
class WeatherLocalDataSourceImpl(
    private val dao: WeatherDao
) : WeatherLocalDataSource {

    override suspend fun addWeathers(weathers: List<Weather>) {
        dao.addWeathers(weathers.asEntities())
    }

    override fun getCurrentWeather(currentDateTime: Long, currentRegionId: Long): Flow<Weather> {
        val dateTime = findClosestWeatherTime(currentDateTime)
        return dao.getCurrentWeather(dateTime, currentRegionId).map { entity ->
            entity?.asDomainModel() ?: Invalid.weather
        }
    }

    override fun getWeatherDates(): Flow<List<String>> {
        return dao.getWeathers().map { entities ->
            entities.map {
                it.dateTime.asDateString()
            }.distinct()
        }
    }

    override fun getWeathersByDate(selectedDate: Long): Flow<List<Weather>> {
        return dao.getWeathersByDateTime(
            startDateTime = selectedDate.toStartDateTime(),
            endDateTime = selectedDate.toEndDateTime()
        ).map { it.asDomainModels() }
    }
}