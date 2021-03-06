package com.highair.weatherforecastid.data.repository

import com.highair.weatherforecastid.data.NetworkBoundResource
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.source.RegionLocalDataSource
import com.highair.weatherforecastid.data.source.RegionRemoteDataSource
import com.highair.weatherforecastid.models.Region
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class RegionRepositoryImpl(
    private val localDataSource: RegionLocalDataSource,
    private val remoteDataSource: RegionRemoteDataSource
) : RegionRepository {

    override fun getRegions(searchQuery: String): Flow<Result<List<Region>>> =
        object : NetworkBoundResource<List<Region>, List<Region>>() {

            override fun shouldFetch(data: List<Region>) =
                searchQuery.isEmpty() && data.isEmpty()

            override suspend fun saveRemoteData(response: List<Region>) =
                localDataSource.insertRegions(response)

            override fun fetchFromLocal() = localDataSource.getRegions().map {
                it.filter { region ->
                    if (searchQuery.isEmpty()) return@filter true
                    region.district.lowercase().contains(searchQuery) or
                            region.province.lowercase().contains(searchQuery)
                }
            }

            override suspend fun fetchFromRemote(): Result<List<Region>> = try {
                remoteDataSource.getRegions().let {
                    Result.Success(it)
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }.asFlow()

    override fun getRegionBy(
        regionId: Long
    ): Flow<Region> = localDataSource.getRegionsById(regionId)
}
