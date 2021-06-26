package com.highair.weatherforecastid.data.source.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.regionEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class RegionLocalDataSourceImplTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var dao: RegionDao
    private lateinit var dataSource: RegionLocalDataSourceImpl

    private val regions = regionEntities

    @Before
    fun setUp() {
        appDatabase = buildTestDb().also {
            dao = it.regionDao()
        }
        dataSource = RegionLocalDataSourceImpl(dao)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun `add regions, inserted on db`(): Unit = runBlocking {
        // given empty data db
        // when add regions
        val regions = regions.asDomainModels()
        dataSource.insertRegions(regions)
        // then inserted on local db
        val result = dataSource.getRegions().first()
        assertThat(result).containsExactlyElementsIn(regions)
    }

    @Test
    fun `get region by invalid id`(): Unit = runBlocking {
        // given empty data db
        // when add regions
        val result = dataSource.getRegionsById(-1).first()
        // then inserted on local db
        assertThat(result.id).isEqualTo(-1)
    }

    @Test
    fun `get region, return expected value`(): Unit = runBlocking {
        // given regions on db
        dao.insertRegions(regions)
        // when get by id
        val expectedRegion = regions[1].asDomainModel()
        val result = dataSource.getRegionsById(expectedRegion.id).first()
        // then return expected value
        assertThat(result).isEqualTo(expectedRegion)
    }
}
