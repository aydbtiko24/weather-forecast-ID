package com.highair.weatherforecastid.data.source.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.weatherEntities
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
class WeatherDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var weatherDao: WeatherDao

    private val weathers = weatherEntities

    @Before
    fun setup() {
        database = buildTestDb().also {
            weatherDao = it.weatherDao()
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insert weathers, added to database`(): Unit = runBlocking {
        // given empty weather on database
        assertThat(weatherDao.getWeathers().first()).isEmpty()

        // when insert weathers to database
        weatherDao.insertWeathers(weathers)

        // then inserted to database
        val result = weatherDao.getWeathers().first()
        assertThat(result).containsExactlyElementsIn(weathers)
    }

    @Test
    fun `add weathers, old data replaced on database`(): Unit = runBlocking {
        // given weathers on database
        val oldWeathers = weathers.subList(0, 3)
        weatherDao.insertWeathers(oldWeathers)

        // when add new weathers data to database
        val newWeathers = weathers.subList(5, 8)
        weatherDao.addWeathers(newWeathers)

        // then old data replaced on database
        val result = weatherDao.getWeathers().first()
        assertThat(result).hasSize(newWeathers.size)
        assertThat(result).containsExactlyElementsIn(newWeathers)
    }

    @Test
    fun `get weathers by date time, return expected values`(): Unit = runBlocking {
        val startDate = 1654966800000 // 2022 06 12 00:00:00
        val endDate = 1655053199000 // 2022 06 12 23:59:59
        // given weathers on database
        weatherDao.insertWeathers(weathers)
        assertThat(weatherDao.getWeathers().first()).isNotEmpty()

        // when get weathers by date time
        val selectedWeathers = weatherDao.getWeathersByDateTime(startDate, endDate).first()

        // then contains expected values
        val expectedWeathers = weathers.subList(8, 12)
        assertThat(selectedWeathers).containsExactlyElementsIn(expectedWeathers)
    }

    @Test
    fun `get current weather, return expected value`(): Unit = runBlocking {
        val weather = weathers[4]
        // given weathers on database
        weatherDao.insertWeathers(weathers)
        assertThat(weatherDao.getWeathers().first()).isNotEmpty()

        // when get weather by date time
        val selectedWeather = weatherDao.getCurrentWeather(
            weather.dateTime,
            weather.regionId
        ).first()

        // then contains expected values
        assertThat(selectedWeather).isEqualTo(weather)
    }
}
