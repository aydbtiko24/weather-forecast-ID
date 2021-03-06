package com.highair.weatherforecastid.data

import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
class FakeRegionRepository : RegionRepository {

    var shouldLoading = false

    private val regions = HashMap<Long, Region>().apply {
        regionEntities.asDomainModels().forEach { region ->
            this[region.id] = region
        }
    }

    override fun getRegions(searchQuery: String) = flow {
        if (shouldLoading) {
            emit(Result.Loading)
        } else {
            emit(Result.Success(regions.values.toList().filter { region ->
                region.district.lowercase().contains(searchQuery) ||
                        region.province.lowercase().contains(searchQuery)
            }))
        }
    }

    override fun getRegionBy(regionId: Long): Flow<Region> {
        return flowOf(regions[regionId] ?: Invalid.region)
    }
}