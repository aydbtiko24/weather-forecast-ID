package com.highair.weatherforecastid.data.repository

import com.highair.weatherforecastid.data.NetworkBoundResource
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.source.WeatherLocalDataSource
import com.highair.weatherforecastid.data.source.WeatherRemoteDataSource
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Weather
import kotlinx.coroutines.flow.Flow

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class WeatherRepositoryImpl(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getCurrentWeather(
        update: Boolean,
        regionId: Long,
        currentDateTime: Long
    ): Flow<Result<Weather>> = object : NetworkBoundResource<Weather, List<Weather>>() {

        override fun shouldFetch(data: Weather) =
            update || data.id == Invalid.id && regionId != Invalid.id

        override suspend fun saveRemoteData(response: List<Weather>) =
            localDataSource.addWeathers(response)

        override fun fetchFromLocal() = localDataSource.getCurrentWeather(
            currentDateTime, regionId
        )

        override suspend fun fetchFromRemote(): Result<List<Weather>> = try {
            remoteDataSource.getWeathers(regionId).let {
                Result.Success(it)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }.asFlow()

    override fun getWeatherDates(): Flow<List<String>> = localDataSource.getWeatherDates()

    override fun getWeathersByDate(selectedDate: Long) = localDataSource.getWeathersByDate(
        selectedDate
    )
}
