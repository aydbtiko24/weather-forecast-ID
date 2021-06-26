package com.highair.weatherforecastid.weather

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.FakePreferencesDataSource
import com.highair.weatherforecastid.data.FakeRegionRepository
import com.highair.weatherforecastid.data.FakeWeatherRepository
import com.highair.weatherforecastid.data.source.local.asDomainModel
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.data.regionEntities
import com.highair.weatherforecastid.data.weatherEntities
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.utils.MainCoroutineRule
import com.highair.weatherforecastid.utils.asDateString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var weatherRepository: FakeWeatherRepository
    private lateinit var regionRepository: FakeRegionRepository
    private lateinit var preferences: FakePreferencesDataSource

    private lateinit var viewModel: WeatherViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        weatherRepository = FakeWeatherRepository()
        regionRepository = FakeRegionRepository()
        preferences = FakePreferencesDataSource()
    }

    private fun initViewModel() {
        viewModel = WeatherViewModel(
            weatherRepository, regionRepository, preferences
        )
    }

    @Test
    fun `update region, state updated`(): Unit = runBlockingTest {
        initViewModel()

        val viewStates = arrayListOf<WeatherViewState>()

        val job = launch {
            viewModel.state.toList(viewStates)
        }

        val region = regionEntities[0].asDomainModel()

        preferences.state.value = region.id

        advanceUntilIdle()

        val regionStates = viewStates.map { it.selectedRegion }

        println("$regionStates")

        assertThat(regionStates).containsExactlyElementsIn(
            listOf(Invalid.region, region, region)
        )

        job.cancel()
    }

    @Test
    fun `set selected date event, state updated`(): Unit = runBlockingTest {
        initViewModel()

        var state: WeatherViewState? = null

        val job = launch {
            viewModel.state.collectLatest {
                state = it
            }
        }

        viewModel.setSelectedDate(weatherEntities[0].dateTime.asDateString())

        advanceUntilIdle()

        println("$state")

        val expectedDate = weatherEntities.subList(0, 4).asDomainModels()

        assertThat(state!!.weathers).containsExactlyElementsIn(
            expectedDate
        )

        job.cancel()
    }

    @Test
    fun `set selected date event, selected option updated`(): Unit = runBlockingTest {
        initViewModel()

        var state: WeatherViewState? = null

        val job = launch {
            viewModel.state.collectLatest {
                state = it
            }
        }

        viewModel.setSelectedDate(weatherEntities[5].dateTime.asDateString())

        advanceUntilIdle()

        println("$state")

        assertThat(state!!.weatherDates[1].selected).isTrue()

        job.cancel()
    }
}
