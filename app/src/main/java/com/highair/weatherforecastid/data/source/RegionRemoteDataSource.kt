package com.highair.weatherforecastid.data.source

import com.highair.weatherforecastid.models.Region

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
interface RegionRemoteDataSource {
    suspend fun getRegions(): List<Region>
}