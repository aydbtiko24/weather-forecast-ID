package com.highair.weatherforecastid.data.repository

import com.highair.weatherforecastid.data.NetworkBoundResource
import com.highair.weatherforecastid.data.source.RegionLocalDataSource
import com.highair.weatherforecastid.data.source.RegionRemoteDataSource
import com.highair.weatherforecastid.models.Region
import kotlinx.coroutines.flow.Flow
import com.highair.weatherforecastid.data.Result

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class RegionRepositoryImpl(
    private val localDataSource: RegionLocalDataSource,
    private val remoteDataSource: RegionRemoteDataSource
) : RegionRepository {

    override fun getRegions(): Flow<Result<List<Region>>> =
        object : NetworkBoundResource<List<Region>, List<Region>>() {

            override fun shouldFetch(data: List<Region>) = data.isEmpty()

            override suspend fun saveRemoteData(response: List<Region>) =
                localDataSource.insertRegions(response)

            override fun fetchFromLocal() = localDataSource.getRegions()

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
