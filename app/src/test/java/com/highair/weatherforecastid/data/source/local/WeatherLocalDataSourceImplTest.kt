package com.highair.weatherforecastid.data.source.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class WeatherLocalDataSourceImplTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var dao: WeatherDao
    private lateinit var dataSource: WeatherLocalDataSourceImpl

    private val weathers = weatherEntities.asDomainModels()

    @Before
    fun setUp() {
        appDatabase = buildTestDb().also {
            dao = it.weatherDao()
        }
        dataSource = WeatherLocalDataSourceImpl(dao)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun `add weathers, inserted on db`(): Unit = runBlocking {
        // given empty data db
        // when add weathers
        dataSource.addWeathers(weathers)
        // then inserted on db
        val result = dao.getWeathers().first()
        assertThat(result).containsExactlyElementsIn(weatherEntities)
    }

    @Test
    fun `get current weather, return expected value`(): Unit = runBlocking {
        // given weathers on db
        dao.insertWeathers(weathers.asEntities())
        // when get current weather
        val currentDateTime = 1623262965000 // 2021 06 10 01:22:45
        val result = dataSource.getCurrentWeather(currentDateTime).first()
        // then return expected value
        val expected = weathers[0]
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `get current weather, return invalid value`(): Unit = runBlocking {
        // given empty weathers on db
        // when get current weather
        val currentDateTime = 1623262965000 // 2021 06 10 01:22:45
        val result = dataSource.getCurrentWeather(currentDateTime).first()
        // then return expected value
        assertThat(result.id).isEqualTo(Constants.invalidId)
    }

    @Test
    fun `get weather dates, return expected value`(): Unit = runBlocking {
        // given weathers on db
        dao.insertWeathers(weathers.asEntities())
        // when get weathers date
        val result = dataSource.getWeatherDates().first()
        // then return expected value
        val expected = listOf(
            "Thu, 10 Jun",
            "Fri, 11 Jun",
            "Sat, 12 Jun"
        )
        assertThat(result).containsExactlyElementsIn(expected)
    }

    @Test
    fun `get weathers by date, return expected value`(): Unit = runBlocking {
        // given weathers on db
        dao.insertWeathers(weathers.asEntities())
        // when get weathers by date
        val currentDateTime = 1623262965000 // 2021 06 10 01:22:45
        val result = dataSource.getWeathersByDate(currentDateTime).first()
        // then return expected value
        val expected = weathers.subList(0, 4)
        assertThat(result).containsExactlyElementsIn(expected)
    }

}