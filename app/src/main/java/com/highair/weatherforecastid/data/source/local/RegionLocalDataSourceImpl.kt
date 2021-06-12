package com.highair.weatherforecastid.data.source.local

import com.highair.weatherforecastid.data.source.RegionLocalDataSource
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class RegionLocalDataSourceImpl(
    private val regionDao: RegionDao
) : RegionLocalDataSource {

    override suspend fun insertRegions(regions: List<Region>) {
        regionDao.insertRegions(regions.asEntities())
    }

    override fun getRegions(): Flow<List<Region>> {
        return regionDao.getRegions().map {
            it.asDomainModels()
        }
    }

    override fun getRegionsById(regionId: Long): Flow<Region> {
        return regionDao.getRegionById(regionId).map {
            it?.asDomainModel() ?: Invalid.region
        }
    }
}