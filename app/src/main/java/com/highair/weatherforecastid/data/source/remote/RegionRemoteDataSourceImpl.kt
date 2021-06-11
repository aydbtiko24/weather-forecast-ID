package com.highair.weatherforecastid.data.source.remote

import com.highair.weatherforecastid.data.source.RegionRemoteDataSource

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class RegionRemoteDataSourceImpl(
    private val apiService: ApiService
) : RegionRemoteDataSource {

    override suspend fun getRegions() = apiService.getRegions().map {
        it.asDomainModel()
    }
}