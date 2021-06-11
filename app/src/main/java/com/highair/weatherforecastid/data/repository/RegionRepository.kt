package com.highair.weatherforecastid.data.repository

import com.highair.weatherforecastid.models.Region
import kotlinx.coroutines.flow.Flow
import com.highair.weatherforecastid.data.Result

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
interface RegionRepository {
    fun getRegions(): Flow<Result<List<Region>>>
    fun getRegionBy(regionId: Long): Flow<Region>
}