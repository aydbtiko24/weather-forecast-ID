package com.highair.weatherforecastid.data.repository

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.source.WeatherLocalDataSource
import com.highair.weatherforecastid.data.source.WeatherRemoteDataSource
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.data.source.local.weatherEntities
import com.highair.weatherforecastid.models.Invalid
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.first
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
class WeatherRepositoryImplTest {

    private val weathers = weatherEntities.asDomainModels()

    @MockK
    lateinit var localDataSource: WeatherLocalDataSource

    @MockK
    lateinit var remoteDataSource: WeatherRemoteDataSource

    private lateinit var weatherRepository: WeatherRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherRepository = WeatherRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `get current weather, data invalid id, fetch remote data source`(): Unit = runBlocking {
        val currentDateTime = 123L
        val regionId = 12L
        val weather = Invalid.weather

        every { localDataSource.getCurrentWeather(currentDateTime, regionId) } returns flowOf(
            weather
        )

        coEvery { localDataSource.addWeathers(weathers) } coAnswers {
            println("inserting data to local data source...")
        }

        coEvery { remoteDataSource.getWeathers(regionId) } returns weathers

        // when get current weather
        val result = weatherRepository.getCurrentWeather(
            update = false,
            regionId = regionId,
            currentDateTime = currentDateTime
        ).toList()

        coVerify(exactly = 1) {
            remoteDataSource.getWeathers(regionId)
        }
        coVerify(exactly = 1) {
            localDataSource.addWeathers(weathers)
        }

        assertThat(result).containsExactlyElementsIn(
            listOf(
                Result.Loading,
                Result.Success(weather)
            )
        )
    }

    @Test
    fun `get current weather, data & region invalid id, shld't fetch remote data source`(): Unit =
        runBlocking {
            val currentDateTime = 123L
            val regionId = Invalid.id
            val weather = Invalid.weather

            every { localDataSource.getCurrentWeather(currentDateTime, regionId) } returns flowOf(
                weather
            )

            coEvery { localDataSource.addWeathers(weathers) } coAnswers {
                println("inserting data to local data source...")
            }

            coEvery { remoteDataSource.getWeathers(regionId) } returns weathers

            // when get current weather
            val result = weatherRepository.getCurrentWeather(
                update = false,
                regionId = regionId,
                currentDateTime = currentDateTime
            ).toList()

            coVerify(exactly = 0) {
                remoteDataSource.getWeathers(regionId)
            }
            coVerify(exactly = 0) {
                localDataSource.addWeathers(weathers)
            }

            assertThat(result).containsExactlyElementsIn(
                listOf(
                    Result.Success(weather)
                )
            )
        }

    @Test
    fun `get weather dates`(): Unit = runBlocking {
        every { localDataSource.getWeatherDates() } returns flowOf(
            listOf("1", "2", "3")
        )

        weatherRepository.getWeatherDates().first()

        verify { localDataSource.getWeatherDates() }
    }

    @Test
    fun `get weathers by date`(): Unit = runBlocking {
        every { localDataSource.getWeathersByDate(2) } returns flowOf(
            weathers.subList(0, 2)
        )

        weatherRepository.getWeathersByDate(2).first()

        verify { localDataSource.getWeathersByDate(2) }
    }
}
