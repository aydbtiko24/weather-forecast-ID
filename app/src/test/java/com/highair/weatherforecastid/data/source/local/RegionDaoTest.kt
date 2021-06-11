package com.highair.weatherforecastid.data.source.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
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
class RegionDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var regionDao: RegionDao

    private val regions = regionEntities

    @Before
    fun setup() {
        database = buildTestDb().also {
            regionDao = it.regionDao()
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insert regions, added to database`(): Unit = runBlocking {
        // given empty weather on database
        assertThat(regionDao.getRegions().first()).isEmpty()

        // when insert regions to database
        regionDao.insertRegions(regions)

        // then inserted to database
        val result = regionDao.getRegions().first()
        assertThat(result).containsExactlyElementsIn(regions)
    }

    @Test
    fun `insert regions, old data replaced on database`(): Unit = runBlocking {
        // given regions on database
        regionDao.insertRegions(regions)

        // when add new regions data to database
        regionDao.insertRegions(regions)

        // then old data replaced on database
        val result = regionDao.getRegions().first()
        assertThat(result).hasSize(regions.size)
        assertThat(result).containsExactlyElementsIn(regions)
    }

    @Test
    fun `insert regions, get by id`(): Unit = runBlocking {
        val region = regions[1]
        // given regions on database
        regionDao.insertRegions(regions)

        // when get  regions by id
        val result = regionDao.getRegionById(region.id).first()

        // then return expected value
        assertThat(result).isEqualTo(region)
    }

}
