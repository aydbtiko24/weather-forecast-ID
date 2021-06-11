package com.highair.weatherforecastid.data.source

import com.highair.weatherforecastid.models.Region
import kotlinx.coroutines.flow.Flow

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
interface RegionLocalDataSource {
    suspend fun insertRegions(regions: List<Region>)
    fun getRegions(): Flow<List<Region>>
    fun getRegionsById(regionId: Long): Flow<Region>
}