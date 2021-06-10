package com.highair.weatherforecastid.data.source

import com.highair.weatherforecastid.models.Weather

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
interface WeatherRemoteDataSource {
    suspend fun getWeathers(regionId: Long): List<Weather>
}
