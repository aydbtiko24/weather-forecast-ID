package com.highair.weatherforecastid.data.repository

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.source.RegionLocalDataSource
import com.highair.weatherforecastid.data.source.RegionRemoteDataSource
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.data.regionEntities
import com.highair.weatherforecastid.models.Region
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class RegionRepositoryImplTest {

    private val regions = regionEntities.asDomainModels()

    @MockK
    lateinit var localDataSource: RegionLocalDataSource

    @MockK
    lateinit var remoteDataSource: RegionRemoteDataSource

    private lateinit var regionRepository: RegionRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        regionRepository = RegionRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `get regions, data empty, fetch remote data source`(): Unit = runBlocking {
        every { localDataSource.getRegions() } returns flowOf(
            emptyList()
        )

        coEvery { remoteDataSource.getRegions() } returns regions

        coEvery { localDataSource.insertRegions(regions) } coAnswers {
            println("inserting data to local data source")
            // let's say data was inserted
            every { localDataSource.getRegions() } returns flowOf(
                regions
            )
        }

        // when get regions
        val result = regionRepository.getRegions("").toList()

        // data refreshed from remote data source
        coVerify(exactly = 1) {
            remoteDataSource.getRegions()
        }

        coVerify(exactly = 1) {
            localDataSource.insertRegions(regions)
        }

        assertThat(result).containsExactlyElementsIn(
            listOf(
                Result.Loading,
                Result.Success(regions)
            )
        )
    }

    @Test
    fun `get regions, data not empty, shld't fetch remote data source`(): Unit = runBlocking {
        every { localDataSource.getRegions() } returns flowOf(
            regions
        )

        coEvery { remoteDataSource.getRegions() } returns regions

        coEvery { localDataSource.insertRegions(regions) } coAnswers {
            println("inserting data to local data source")
            // let's say data was inserted
            every { localDataSource.getRegions() } returns flowOf(
                regions
            )
        }

        // when get regions
        val result = regionRepository.getRegions("").toList()

        // data shouldn't refreshed from remote data source
        coVerify(exactly = 0) {
            remoteDataSource.getRegions()
        }

        coVerify(exactly = 0) {
            localDataSource.insertRegions(regions)
        }

        assertThat(result).containsExactlyElementsIn(
            listOf(
                Result.Success(regions)
            )
        )
    }

    @Test
    fun `get region by region id`(): Unit = runBlocking {
        val regionId = 1L

        every { localDataSource.getRegionsById(regionId) } returns flowOf(
            regions[1]
        )

        regionRepository.getRegionBy(regionId)

        verify { localDataSource.getRegionsById(regionId) }
    }

    @Test
    fun `search regions, search query not empty, shld't fetch remote`(): Unit = runBlocking {
        every { localDataSource.getRegions() } returns flowOf(
            emptyList()
        )

        coEvery { remoteDataSource.getRegions() } returns regions

        coEvery { localDataSource.insertRegions(regions) } coAnswers {
            println("inserting data to local data source")
            // let's say data was inserted
            every { localDataSource.getRegions() } returns flowOf(
                regions
            )
        }

        // when get regions
        val result = regionRepository.getRegions("search query").toList()

        // data shouldn't refreshed from remote data source
        coVerify(exactly = 0) {
            remoteDataSource.getRegions()
        }

        coVerify(exactly = 0) {
            localDataSource.insertRegions(regions)
        }

        assertThat(result).containsExactlyElementsIn(
            listOf(
                Result.Success(emptyList<Region>())
            )
        )
    }

    @Test
    fun `search regions, contain expected value`(): Unit = runBlocking {
        val region = regions[1]

        every { localDataSource.getRegions() } returns flowOf(
            regions
        )

        // when get regions
        val result = regionRepository.getRegions(region.district.lowercase()).toList()

        assertThat(result).containsExactlyElementsIn(
            listOf(
                Result.Success(
                    listOf(region)
                )
            )
        )
    }
}
