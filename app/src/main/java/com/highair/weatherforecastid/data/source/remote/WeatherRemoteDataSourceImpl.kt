package com.highair.weatherforecastid.data.source.remote

import com.highair.weatherforecastid.data.source.WeatherRemoteDataSource
import com.highair.weatherforecastid.models.Weather

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
class WeatherRemoteDataSourceImpl(
    private val apiService: ApiService
) : WeatherRemoteDataSource {

    override suspend fun getWeathers(
        regionId: Long
    ): List<Weather> {
        return apiService.getWeathers(regionId).map {
            it.asDomainModel()
        }
    }
}